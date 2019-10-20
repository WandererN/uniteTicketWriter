package com.jh.uniteticketwriter.ui.write.wifi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jh.uniteticketwriter.nfc.message.CustomNfcMessage
import com.jh.uniteticketwriter.nfc.message.wifi.AuthTypes
import com.jh.uniteticketwriter.nfc.message.wifi.WifiNfcMessage
import com.jh.uniteticketwriter.nfc.message.wifi.WifiRecord
import com.jh.uniteticketwriter.ui.write.WriteViewModel

class WriteWifiApViewModel : ViewModel(), WriteViewModel {
    override fun validate(): Boolean {
        return !ssid.value.isNullOrEmpty()
    }

    override fun getMessage(): CustomNfcMessage<*> {
        return WifiNfcMessage(
            WifiRecord(
                AuthTypes.fromInt(authType.value ?: 0)
                    ?: AuthTypes.NO_AUTH,
                ssid.value ?: "",
                password.value ?: "",
                userName.value ?: ""
            )
        )
    }

    // TODO: Implement the ViewModel
    var authType = MutableLiveData<Int>() //TODO make spinner data binding
    val ssid = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val userName = MutableLiveData<String>()
    val userNameVisibility = MutableLiveData<Int>()

    init {
        userNameVisibility.value = 1
    }
}
