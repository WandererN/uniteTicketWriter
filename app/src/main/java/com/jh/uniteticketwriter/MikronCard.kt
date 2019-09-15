package com.jh.uniteticketwriter

import android.nfc.Tag
import android.nfc.tech.NfcA
import java.io.ByteArrayOutputStream
import java.io.IOException

class MikronCard(val tag: Tag) {
    private val READ_CMD = (0x30 and 0xFF).toByte()
    private val WRITE_CMD = (0xA2 and 0xFF).toByte()
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
        baos.apply {
            write(WRITE_CMD.toInt())
            write(section.toInt())
            write(payload.copyOfRange(0, 4))
        }
        val message = baos.toByteArray()
        baos.close()
        manager?.transceive(message)
        return true//TODO handle ack
    }

}