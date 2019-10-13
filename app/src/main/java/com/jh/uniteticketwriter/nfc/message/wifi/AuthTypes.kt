package com.jh.uniteticketwriter.nfc.message.wifi

enum class AuthTypes(val value: Int) {
    NO_AUTH(0),
    WEP(1),
    WPA2_PSK(2),
    WPA2_ENTERPRIZE(3);

    fun toInt() = value

    companion object {
        private val map = values().associateBy(AuthTypes::value)
        fun fromInt(value: Int) = map[value]
    }
}