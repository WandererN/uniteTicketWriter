package com.jh.uniteticketwriter.nfc.message.wifi

import com.jh.uniteticketwriter.extensions.readString
import com.jh.uniteticketwriter.extensions.writeString
import com.jh.uniteticketwriter.nfc.message.CustomNfcMessage
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class WifiNfcMessage(override var message: WifiRecord? = null) :
    CustomNfcMessage<WifiRecord> {
    override val type: Int = MessageNfcTypes.WIFI.toInt()

    override fun toByteArray(): ByteArray = ByteArrayOutputStream().apply {
        use {
            message?.let { mess ->
                it.write(mess.authType.toInt())
                it.writeString(mess.ssid)
                when (mess.authType) {
                    AuthTypes.NO_AUTH -> {
                    }
                    AuthTypes.WEP, AuthTypes.WPA2_PERSONAL -> it.writeString(mess.password)
                    AuthTypes.WPA2_ENTERPRISE -> {
                        it.writeString(mess.userName)
                        it.writeString(mess.password)
                    }
                }
            }
        }
    }.toByteArray()

    override fun parse(data: ByteArray) = apply {
        ByteArrayInputStream(data).use {
            val authType = it.read()
            val ssid = it.readString()
            val auth = AuthTypes.fromInt(authType)
            var userName = ""
            auth?.let { a ->
                val pass = when (a) {
                    AuthTypes.NO_AUTH -> ""
                    AuthTypes.WEP, AuthTypes.WPA2_PERSONAL -> it.readString()
                    AuthTypes.WPA2_ENTERPRISE -> {
                        userName = it.readString()
                        it.readString()
                    }
                }
                message = WifiRecord(a, ssid, pass, userName)
            }
        }
    }
}