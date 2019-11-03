package com.jh.uniteticketwriter

import android.content.res.Resources
import com.jh.uniteticketwriter.nfc.MikronCard
import com.jh.uniteticketwriter.nfc.MikronCardManager

object Config {
    var currentCard: MikronCard? = null
    var currentManager = MikronCardManager()
    var resources:Resources? = null
}