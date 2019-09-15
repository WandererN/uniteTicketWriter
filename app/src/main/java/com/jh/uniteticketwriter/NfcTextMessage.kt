package com.jh.uniteticketwriter

import com.jh.uniteticketwriter.exceptions.MessageNotSetException

class NfcTextMessage : NfcCustomMessage<String> {
    constructor()
    constructor(message: String) {
        this.message = message
    }

    override fun parse(data: ByteArray) {
        message = String(data)
    }

    override fun toByteArray(): ByteArray = message?.toByteArray() ?: throw MessageNotSetException()

    override var message: String? = null
}