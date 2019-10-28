package com.jh.uniteticketwriter.nfc.message.bluetooth

import com.jh.uniteticketwriter.extensions.readString
import com.jh.uniteticketwriter.extensions.writeString
import com.jh.uniteticketwriter.nfc.message.CustomNfcMessage
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class BluetoothNfcMessage(override var message: BluetoothRecord? = null) :
    CustomNfcMessage<BluetoothRecord> {

    override val type = MessageNfcTypes.BLUETOOTH.toInt()

    override fun toByteArray(): ByteArray {
        return ByteArrayOutputStream().apply {
            use { it ->
                message?.let { mess ->
                    it.writeString(mess.name)
                    it.writeString(mess.mac)
                }
            }
        }.toByteArray()
    }

    override fun parse(data: ByteArray): CustomNfcMessage<BluetoothRecord> {
        ByteArrayInputStream(data).use {
            message = BluetoothRecord(name = it.readString(), mac = it.readString())
        }
        return this
    }

}