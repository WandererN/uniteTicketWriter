package com.jh.uniteticketwriter.nfc.message.text

import com.jh.uniteticketwriter.exceptions.MessageNotSetException
import com.jh.uniteticketwriter.nfc.message.CustomNfcMessage
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes

class TextNfcMessage(override var message: String? = null) :
    CustomNfcMessage<String> {
    override val type: Int = MessageNfcTypes.TEXT.toInt()

    override fun parse(data: ByteArray): TextNfcMessage {
        message = String(data)
        return this
    }

    override fun toByteArray(): ByteArray = message?.toByteArray() ?: throw MessageNotSetException()


}