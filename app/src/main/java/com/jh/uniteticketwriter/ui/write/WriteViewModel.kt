package com.jh.uniteticketwriter.ui.write

interface WriteViewModel {
    fun validate(): Boolean
    fun getBytesToWrite(): ByteArray
}