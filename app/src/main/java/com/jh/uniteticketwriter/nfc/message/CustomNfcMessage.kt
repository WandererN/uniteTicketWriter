package com.jh.uniteticketwriter.nfc.message

interface CustomNfcMessage<T> {
    fun toByteArray(): ByteArray
    fun parse(data: ByteArray): CustomNfcMessage<T>
    var message: T?
    val type: Int
}