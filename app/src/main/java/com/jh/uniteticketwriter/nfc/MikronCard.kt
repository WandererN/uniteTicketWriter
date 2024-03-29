package com.jh.uniteticketwriter.nfc

import android.nfc.Tag
import android.nfc.tech.NfcA
import java.io.ByteArrayOutputStream
import java.io.IOException

class MikronCard(tag: Tag) {

    private var manager: NfcA? = null

    init {
        manager = NfcA.get(tag)
    }

    fun connect() = manager?.connect()

    fun close() = manager?.close()

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

    companion object {
        private const val WRITE_CMD = 0xA2.toByte()
        private const val READ_CMD = 0x30.toByte()
    }

}