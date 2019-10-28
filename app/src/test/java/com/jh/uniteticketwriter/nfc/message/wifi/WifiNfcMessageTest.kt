package com.jh.uniteticketwriter.nfc.message.wifi

import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe

class WifiNfcMessageTest : StringSpec() {

    init {
        "check wifi message with no auth serialization/deserialization" {
            val wifiRecord = WifiRecord(AuthTypes.NO_AUTH, "wifiNet")
            val wifiMessage = WifiNfcMessage(wifiRecord)

            WifiNfcMessage().parse(wifiMessage.toByteArray()).message shouldBe wifiRecord
        }
        "check wifi message with wep serialization/deserialization" {
            val wifiRecord = WifiRecord(AuthTypes.WEP, "wifiNet", "password")
            val wifiMessage = WifiNfcMessage(wifiRecord)

            WifiNfcMessage().parse(wifiMessage.toByteArray()).message shouldBe wifiRecord
        }
        "check wifi message with wpa2 serialization/deserialization" {
            val wifiRecord = WifiRecord(AuthTypes.WPA2_PSK, "wifiNet", "password")
            val wifiMessage = WifiNfcMessage(wifiRecord)

            WifiNfcMessage().parse(wifiMessage.toByteArray()).message shouldBe wifiRecord
        }
        "check wifi message with wpa2 enterprise serialization/deserialization" {
            val wifiRecord = WifiRecord(AuthTypes.WPA2_PSK, "wifiNet", "password", "username")
            val wifiMessage = WifiNfcMessage(wifiRecord)

            WifiNfcMessage().parse(wifiMessage.toByteArray()).message shouldBe wifiRecord
        }

    }

}