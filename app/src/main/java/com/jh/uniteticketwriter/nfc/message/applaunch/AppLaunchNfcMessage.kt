package com.jh.uniteticketwriter.nfc.message.applaunch

import com.jh.uniteticketwriter.extensions.readString
import com.jh.uniteticketwriter.extensions.writeString
import com.jh.uniteticketwriter.nfc.message.CustomNfcMessage
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class AppLaunchNfcMessage(override var message: AppLaunchRecord? = null) :
    CustomNfcMessage<AppLaunchRecord> {

    override val type = MessageNfcTypes.APP_LAUNCH.toInt()

    override fun toByteArray(): ByteArray = ByteArrayOutputStream().apply {
        use {
            message?.let { mess ->
                it.writeString(mess.appName)
                it.writeString(mess.appPackage)
            }
        }
    }.toByteArray()

    override fun parse(data: ByteArray) = apply {
        ByteArrayInputStream(data).use {
            message = AppLaunchRecord(it.readString(), it.readString())
        }
    }

}