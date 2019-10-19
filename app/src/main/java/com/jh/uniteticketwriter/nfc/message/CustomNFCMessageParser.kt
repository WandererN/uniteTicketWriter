package com.jh.uniteticketwriter.nfc.message

import com.jh.uniteticketwriter.nfc.message.applaunch.AppLaunchNfcMessage
import com.jh.uniteticketwriter.nfc.message.bluetooth.BluetoothNfcMessage
import com.jh.uniteticketwriter.nfc.message.text.TextNfcMessage
import com.jh.uniteticketwriter.nfc.message.wifi.WifiNfcMessage

object CustomNFCMessageParser {

    fun parse(data: ByteArray, type: MessageNfcTypes): CustomNfcMessage<*> {
        return when (type) {
            MessageNfcTypes.TEXT -> TextNfcMessage()
            MessageNfcTypes.WIFI -> WifiNfcMessage()
            MessageNfcTypes.BLUETOOTH -> BluetoothNfcMessage()
            MessageNfcTypes.APP_LAUNCH -> AppLaunchNfcMessage()
        }.parse(data)
    }
}