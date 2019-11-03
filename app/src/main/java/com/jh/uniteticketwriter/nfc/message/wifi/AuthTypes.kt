package com.jh.uniteticketwriter.nfc.message.wifi

import com.jh.uniteticketwriter.Config
import com.jh.uniteticketwriter.R

enum class AuthTypes(val value: Int) {
    NO_AUTH(0),
    WEP(1),
    WPA2_PERSONAL(2),
    WPA2_ENTERPRISE(3);

    fun toInt() = value
    override fun toString(): String =
        Config.resources?.getStringArray(R.array.wifi_auth_types)?.get(value) ?: ""

    companion object {
        private val map = values().associateBy(AuthTypes::value)
        fun fromInt(value: Int) = map[value]
    }
}