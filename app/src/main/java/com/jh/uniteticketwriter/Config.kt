package com.jh.uniteticketwriter

import com.jh.uniteticketwriter.nfc.MikronCard
import com.jh.uniteticketwriter.nfc.MikronCardManager

object Config {
    var currentCard: MikronCard? = null
    var currentManager = MikronCardManager()
}