package com.jh.uniteticketwriter

interface NfcCustomMessage<T> {
    fun toByteArray():ByteArray
    fun parse(data: ByteArray)
    var message: T?

}