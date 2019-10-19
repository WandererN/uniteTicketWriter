package com.jh.uniteticketwriter.nfc.message.text

import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe

class NfcTextMessageTest : StringSpec() {

    init {
        "check text message serialization/deserialization" {
            val message = "TEST"
            val textMessage = NfcTextMessage(message)

            NfcTextMessage().parse(textMessage.toByteArray()).message shouldBe message

        }
    }

}