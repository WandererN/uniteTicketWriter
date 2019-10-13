package com.jh.uniteticketwriter.nfc.message.text

import com.jh.uniteticketwriter.exceptions.MessageNotSetException
import com.jh.uniteticketwriter.nfc.message.NfcCustomMessage
import com.jh.uniteticketwriter.nfc.message.NfcMessageTypes

class NfcTextMessage(override var message: String? = null) :
    NfcCustomMessage<String> {
    override val type: Int = NfcMessageTypes.TEXT.toInt()

    override fun parse(data: ByteArray): NfcTextMessage {
        message = String(data)
        return this
    }

    override fun toByteArray(): ByteArray = message?.toByteArray() ?: throw MessageNotSetException()


}