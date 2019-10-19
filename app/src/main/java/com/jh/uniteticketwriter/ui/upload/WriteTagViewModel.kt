package com.jh.uniteticketwriter.ui.upload

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes.*
import com.jh.uniteticketwriter.ui.upload.applaunch.WriteAppLaunchFragment
import com.jh.uniteticketwriter.ui.upload.bluetooth.WriteBluetoothFragment
import com.jh.uniteticketwriter.ui.upload.text.WriteTextFragment
import com.jh.uniteticketwriter.ui.upload.wifi.WriteWifiApFragment

class WriteTagViewModel : ViewModel() {
    private val fragments = hashMapOf(
        TEXT to WriteTextFragment(),
        WIFI to WriteWifiApFragment(),
        BLUETOOTH to WriteBluetoothFragment(),
        APP_LAUNCH to WriteAppLaunchFragment()
    )

    // TODO: Implement the ViewModel
    fun getFragmentByMessageType(type: MessageNfcTypes): Fragment {
        return fragments[type] ?: WriteTagFragmentHost()
    }
}
