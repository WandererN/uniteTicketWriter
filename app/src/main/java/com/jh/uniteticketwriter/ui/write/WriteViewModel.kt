package com.jh.uniteticketwriter.ui.write

import com.jh.uniteticketwriter.nfc.message.CustomNfcMessage

interface WriteViewModel {
    fun validate(): Boolean
    fun getMessage(): CustomNfcMessage<*>
}