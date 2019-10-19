package com.jh.uniteticketwriter.nfc.message.applaunch

import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe

class AppLaunchNfcMessageTest : StringSpec() {

    init {
        "check app launch message serialization/deserialization" {
            val testRecord = AppLaunchRecord("Test", "com.test.app")
            val mess = AppLaunchNfcMessage(testRecord)
            val parsedMess = AppLaunchNfcMessage().parse(mess.toByteArray())
            parsedMess.message shouldBe testRecord
        }
    }

}