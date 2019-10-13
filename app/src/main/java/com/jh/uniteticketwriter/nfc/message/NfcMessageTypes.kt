package com.jh.uniteticketwriter.nfc.message

enum class NfcMessageTypes(val value: Int) {
    TEXT(1),
    WIFI(2),
    BLUETOOTH(3),
    CONTACT(4);

    fun toInt(): Int = value

    companion object {
        private val map = values().associateBy(NfcMessageTypes::value)
        fun fromInt(type: Int) = map[type]
    }
}