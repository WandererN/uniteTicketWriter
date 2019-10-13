package com.jh.uniteticketwriter.nfc.message.wifi

class WifiRecord(
    var authType: AuthTypes = AuthTypes.NO_AUTH,
    var ssid: String = "",
    var password: String = "",
    var userName: String = ""
)