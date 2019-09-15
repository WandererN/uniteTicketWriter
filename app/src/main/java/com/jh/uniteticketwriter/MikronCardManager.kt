package com.jh.uniteticketwriter

import android.nfc.NdefMessage
import android.nfc.Tag
import android.nfc.tech.MifareUltralight
import java.io.ByteArrayInputStream
import java.io.IOException
import kotlin.experimental.and
import kotlin.math.min

class MikronCardManager {
    private val locks = ByteArray(5)
    private val lockedPages = HashSet<Int>()
    private val sectionSize = 4
    private val startSection = 4 //TODO MOVE TO CONSTANTS
    private val stopSection = 35
    private val lockInformationPageFirst = 2
    private val lockInformationPageSecond = 36

    val availableSizeBytes: Int
        get() {
            val maximumSize = (stopSection - startSection)
            return (maximumSize - lockedPages.size) * sectionSize
        }

    private var mfc: MifareUltralight? = null
    private fun getBitValue(byte: Byte, num: Int): Boolean {
        return byte and (1 shl num).toByte() == 1.toByte()
    }

    private fun isPageLocked(num: Int): Boolean {//num should be 4..35
        val sectionWithIndividualLockInfo: Int
        val bitNumberInByte: Int
        val lockedBlock: Boolean
        if (num < 16) {
            lockedBlock = when (num) {
                3 -> getBitValue(locks[0], 0)
                in 4..9 -> getBitValue(locks[0], 1)
                in 10..15 -> getBitValue(locks[0], 2)
                else -> false
            }
            sectionWithIndividualLockInfo = num / 8
            bitNumberInByte = (num - 1) % 8

        } else {
            sectionWithIndividualLockInfo = ((num - 16) / 2 + 16) / 8
            bitNumberInByte = ((num - 1) / 2) % 8
            lockedBlock = getBitValue(locks[3], (num - 16) / 3 - 1)
        }
        return getBitValue(locks[sectionWithIndividualLockInfo], bitNumberInByte) || lockedBlock
    }

    private fun readLockInformation() {
        try {
            val readPagesFirst = mfc?.readPages(lockInformationPageFirst) ?: ByteArray(4)
            val readPagesSecond = mfc?.readPages(lockInformationPageSecond) ?: ByteArray(4)
            readPagesFirst.copyInto(locks, 0, 2, 3)
            readPagesSecond.copyInto(locks, 2, 0, 2)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun updateLockInformation() {
        lockedPages.clear()
        readLockInformation()
        for (i in startSection..stopSection)
            if (isPageLocked(i))
                lockedPages.add(i)
    }

    fun connect(tag: Tag) {
        mfc = MifareUltralight.get(tag)
        mfc?.connect()
        updateLockInformation()
    }


    fun writeNdef(messageNdef: NdefMessage) {
        val messageBytes = messageNdef.toByteArray()
        val sectionBuffer = ByteArray(sectionSize)
        if (messageBytes.size > availableSizeBytes)
            throw NotEnoughSpaceException()
        val bytesStream = ByteArrayInputStream(messageBytes)
        var currentSection = min(startSection, lockedPages.min() ?: startSection)
        while (bytesStream.available() > 0) {
            val readSize = bytesStream.read(sectionBuffer)
            mfc?.writePage(currentSection, sectionBuffer.copyOfRange(0, readSize))
            currentSection++
        }
    }
}