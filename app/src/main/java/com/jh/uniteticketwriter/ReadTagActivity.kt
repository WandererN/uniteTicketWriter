package com.jh.uniteticketwriter

import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.IOException

class ReadTagActivity : AppCompatActivity() {
    private val LOGTAG = ReadTagActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_for_tag)
        val cardManager = MikronCardManager()
        if ("android.nfc.action.TECH_DISCOVERED" == intent.action) {
            try {
                val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
                cardManager.connect(tag)
                Log.d(LOGTAG, "Read Card! \n Available ${cardManager.availableSizeBytes} bytes")
                Log.d(LOGTAG, "Card content: ${cardManager.readAllRaw().toTypedArray().toHexString(4)}")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
