package com.jh.uniteticketwriter.nfc.message.bluetooth

import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe

class BluetoothNfcMessageTest : StringSpec() {

    init {
        "check bluetooth message serialization/deserialization" {
            val message = BluetoothRecord("22:22:33:33","testBtDevice")
            val btMessage = BluetoothNfcMessage(message)

            BluetoothNfcMessage().parse(btMessage.toByteArray()).message shouldBe message

        }
    }

}