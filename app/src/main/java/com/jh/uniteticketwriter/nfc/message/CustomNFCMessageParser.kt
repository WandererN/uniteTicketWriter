package com.jh.uniteticketwriter.nfc.message

import com.jh.uniteticketwriter.nfc.message.bluetooth.NfcBluetoothMessage
import com.jh.uniteticketwriter.nfc.message.text.NfcTextMessage
import com.jh.uniteticketwriter.nfc.message.wifi.NfcWifiMessage

object CustomNFCMessageParser {
    fun parse(data: ByteArray, type: NfcMessageTypes): NfcCustomMessage<*> {
        return when (type) {
            NfcMessageTypes.TEXT -> NfcTextMessage()
            NfcMessageTypes.WIFI -> NfcWifiMessage()
            NfcMessageTypes.BLUETOOTH -> NfcBluetoothMessage()
            NfcMessageTypes.CONTACT -> TODO()
        }.parse(data)
    }
}