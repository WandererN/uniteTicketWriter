package com.jh.uniteticketwriter.nfc

import android.nfc.Tag
import android.nfc.tech.NfcA
import java.io.ByteArrayOutputStream
import java.io.IOException

class MikronCard(tag: Tag) {

    private val READ_CMD = 0x30.toByte()
    private val WRITE_CMD = 0xA2.toByte()
    private val START_SECTION = 4
    private val END_SECTION = 35
    private var manager: NfcA? = null

    init {
        manager = NfcA.get(tag)
    }

    fun connect() = manager?.connect()

    fun readPages(section: Byte): ByteArray =
        manager?.transceive(byteArrayOf(READ_CMD, section))?.copyOfRange(0, 4)
            ?: throw IOException()

    fun writePage(section: Byte, payload: ByteArray): Boolean {
        val baos = ByteArrayOutputStream()
        val buf = ByteArray(4)
        baos.apply {
            write(WRITE_CMD.toInt())
            write(section.toInt())
            buf.fill(0)
            payload.copyInto(buf)
            write(buf)
        }
        val message = baos.toByteArray()
        try {
            manager?.transceive(message)
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
        baos.close()
        return true//TODO handle ack
    }

}