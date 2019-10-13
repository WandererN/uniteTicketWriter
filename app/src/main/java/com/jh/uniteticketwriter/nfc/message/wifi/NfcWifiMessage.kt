package com.jh.uniteticketwriter.nfc.message.wifi

import com.jh.uniteticketwriter.extensions.readString
import com.jh.uniteticketwriter.extensions.writeString
import com.jh.uniteticketwriter.nfc.message.NfcCustomMessage
import com.jh.uniteticketwriter.nfc.message.NfcMessageTypes
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class NfcWifiMessage(override var message: WifiRecord? = null) :
    NfcCustomMessage<WifiRecord> {
    override val type: Int = NfcMessageTypes.WIFI.toInt()

    override fun toByteArray(): ByteArray = ByteArrayOutputStream().apply {
        use {
            message?.let { mess ->
                it.write(mess.authType.toInt())
                it.writeString(mess.ssid)
                when (mess.authType) {
                    AuthTypes.NO_AUTH -> {
                    }
                    AuthTypes.WEP, AuthTypes.WPA2_PSK -> it.writeString(mess.password)
                    AuthTypes.WPA2_ENTERPRIZE -> {
                        it.writeString(mess.userName)
                        it.writeString(mess.password)
                    }
                }
            }
        }
    }.toByteArray()

    override fun parse(data: ByteArray): NfcWifiMessage {
        ByteArrayInputStream(data).use {
            val authType = it.read()
            val ssid = it.readString()
            val auth = AuthTypes.fromInt(authType)
            var userName = ""
            auth?.let { a ->
                val pass = when (a) {
                    AuthTypes.NO_AUTH -> ""
                    AuthTypes.WEP, AuthTypes.WPA2_PSK -> it.readString()
                    AuthTypes.WPA2_ENTERPRIZE -> {
                        userName = it.readString()
                        it.readString()
                    }
                }
                message = WifiRecord(a, ssid, pass, userName)
            }

        }
        return this
    }
}