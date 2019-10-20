package com.jh.uniteticketwriter.ui.write

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.jh.uniteticketwriter.Config
import com.jh.uniteticketwriter.nfc.message.CustomNfcMessage
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes.*
import com.jh.uniteticketwriter.ui.write.applaunch.WriteAppLaunchFragment
import com.jh.uniteticketwriter.ui.write.bluetooth.WriteBluetoothFragment
import com.jh.uniteticketwriter.ui.write.text.WriteTextFragment
import com.jh.uniteticketwriter.ui.write.wifi.WriteWifiApFragment

class WriteTagViewModel : ViewModel() {
    private var cardManager = Config.currentManager

    private val fragments = hashMapOf(
        TEXT to WriteTextFragment(),
        WIFI to WriteWifiApFragment(),
        BLUETOOTH to WriteBluetoothFragment(),
        APP_LAUNCH to WriteAppLaunchFragment()
    )
    var childViewModel: WriteViewModel? = null
    fun getFragmentByMessageType(type: MessageNfcTypes): Fragment {
        return fragments[type] ?: WriteTagFragmentHost()
    }

    fun writeTag() {
        childViewModel?.let {
            if(it.validate()) {
                cardManager.writeNdef(it.getMessage())
            }
        }
    }
}
