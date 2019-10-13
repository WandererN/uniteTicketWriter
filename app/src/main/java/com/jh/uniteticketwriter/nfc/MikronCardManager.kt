package com.jh.uniteticketwriter.nfc

import android.nfc.Tag
import com.jh.uniteticketwriter.Config
import com.jh.uniteticketwriter.nfc.message.NfcCustomMessage
import com.jh.uniteticketwriter.exceptions.NotEnoughSpaceException
import com.jh.uniteticketwriter.exceptions.UnknownMessageType
import com.jh.uniteticketwriter.nfc.message.CustomNFCMessageParser
import com.jh.uniteticketwriter.nfc.message.NfcMessageTypes
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
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

    private var mfc: MikronCard? = null
    private fun getBitValue(byte: Byte, num: Int): Boolean {
        val m = (byte.toInt() shr num).toByte() and 1
        return m == 1.toByte()
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
            bitNumberInByte = num % 8

        } else {
            sectionWithIndividualLockInfo = ((num - 16) / 2 + 16) / 8
            bitNumberInByte = ((num - 1) / 2) % 8
            lockedBlock = getBitValue(locks[3], (num - 16) / 3 - 1)
        }
        return getBitValue(locks[sectionWithIndividualLockInfo], bitNumberInByte) || lockedBlock
    }

    private fun readLockInformation() {
        try {
            val readPagesFirst = mfc?.readPages(lockInformationPageFirst.toByte()) ?: ByteArray(4)
            val readPagesSecond = mfc?.readPages(lockInformationPageSecond.toByte()) ?: ByteArray(4)
            readPagesFirst.copyInto(locks, 0, 2, 4)
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
        mfc = MikronCard(tag)
        Config.currentCard = mfc
        mfc?.connect()
        updateLockInformation()
    }

    private fun getFirstWritableSection() = if (!isPageLocked(startSection)) startSection else {
        var n = startSection
        while (n in lockedPages) {
            n++
        }
        n
    }


    fun writeNdef(messageNdef: NfcCustomMessage<*>) {
        val messageBytes = messageNdef.toByteArray()

        val fullMessageBytes = ByteArrayOutputStream().apply {
            use {
                it.write(messageBytes.size)
                it.write(messageNdef.type.toInt())
                it.write(messageBytes)
            }
        }.toByteArray()

        val sectionBuffer = ByteArray(sectionSize)
        if (fullMessageBytes.size > availableSizeBytes)
            throw NotEnoughSpaceException()

        val bytesStream = ByteArrayInputStream(fullMessageBytes)
        var currentSection = getFirstWritableSection()
        while (bytesStream.available() > 0) {
            if (currentSection !in lockedPages) {
                val readSize = bytesStream.read(sectionBuffer)
                mfc?.writePage(currentSection.toByte(), sectionBuffer.copyOfRange(0, readSize))
            }
            currentSection++
        }
    }

    fun readNdef(): NfcCustomMessage<*> {
        val baos = ByteArrayOutputStream()
        val firstSection = getFirstWritableSection()
        var ndefSize = 0
        var ndefType: NfcMessageTypes? = null
        for (i in firstSection..stopSection) {
            if (i !in lockedPages) {
                val page = mfc?.readPages(i.toByte()) ?: throw IOException()

                if (i == firstSection) {
                    ndefSize = page[0].toInt()
                    ndefType = NfcMessageTypes.fromInt(page[1].toInt())
                    baos.write(page.copyOfRange(2, 4))
                } else {
                    baos.write(page.copyOfRange(0, min(ndefSize - baos.size(), page.size)))
                }
            }
            if (baos.size() == ndefSize)
                break
        }
        ndefType?.let {
            return CustomNFCMessageParser.parse(baos.toByteArray(), ndefType)
        }
        throw UnknownMessageType()
    }

    fun readAllRaw(): ByteArray {
        val bis = ByteArrayOutputStream()
        for (i in 0..40)
            bis.write(mfc?.readPages(i.toByte()))
        return bis.toByteArray()
    }
}