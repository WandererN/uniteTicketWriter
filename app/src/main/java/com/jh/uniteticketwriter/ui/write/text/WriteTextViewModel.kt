package com.jh.uniteticketwriter.ui.write.text

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jh.uniteticketwriter.nfc.message.text.TextNfcMessage
import com.jh.uniteticketwriter.ui.write.WriteViewModel

class WriteTextViewModel : ViewModel(), WriteViewModel {
    val text = MutableLiveData<String>()
    override fun validate(): Boolean {
        return true
    }

    override fun getBytesToWrite(): ByteArray {
        return TextNfcMessage(text.value).toByteArray()
    }
}
