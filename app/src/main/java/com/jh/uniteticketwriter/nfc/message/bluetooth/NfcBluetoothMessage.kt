package com.jh.uniteticketwriter.nfc.message.bluetooth

import com.jh.uniteticketwriter.extensions.readString
import com.jh.uniteticketwriter.extensions.writeString
import com.jh.uniteticketwriter.nfc.message.NfcCustomMessage
import com.jh.uniteticketwriter.nfc.message.NfcMessageTypes
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class NfcBluetoothMessage(override var message: BluetoothRecord? = null) :
    NfcCustomMessage<BluetoothRecord> {

    override val type = NfcMessageTypes.BLUETOOTH.toInt()

    override fun toByteArray(): ByteArray {
        return ByteArrayOutputStream().apply {
            use { baos ->
                message?.let { mess ->
                    baos.writeString(mess.name)
                    baos.writeString(mess.mac)
                }
            }
        }.toByteArray()
    }

    override fun parse(data: ByteArray): NfcCustomMessage<BluetoothRecord> {
        ByteArrayInputStream(data).use {
            message = BluetoothRecord(it.readString(), it.readString())
        }
        return this
    }

}