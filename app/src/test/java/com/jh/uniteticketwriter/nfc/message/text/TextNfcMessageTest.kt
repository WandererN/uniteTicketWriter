package com.jh.uniteticketwriter.nfc.message.text

import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe

class TextNfcMessageTest : StringSpec() {

    init {
        "check text message serialization/deserialization" {
            val message = "TEST"
            val textMessage = TextNfcMessage(message)

            TextNfcMessage().parse(textMessage.toByteArray()).message shouldBe message

        }
    }

}