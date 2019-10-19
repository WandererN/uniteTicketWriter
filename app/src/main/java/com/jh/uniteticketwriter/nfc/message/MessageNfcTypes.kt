package com.jh.uniteticketwriter.nfc.message

enum class MessageNfcTypes(val value: Int) {
    TEXT(1),
    WIFI(2),
    BLUETOOTH(3),
    APP_LAUNCH(4);

    fun toInt(): Int = value

    companion object {
        private val map = values().associateBy(MessageNfcTypes::value)
        fun fromInt(type: Int) = map[type]
    }
}