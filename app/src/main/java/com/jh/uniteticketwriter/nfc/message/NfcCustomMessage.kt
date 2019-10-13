package com.jh.uniteticketwriter.nfc.message

interface NfcCustomMessage<T> {
    fun toByteArray(): ByteArray
    fun parse(data: ByteArray): NfcCustomMessage<T>
    var message: T?
    val type: Int
}