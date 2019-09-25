package com.jh.uniteticketwriter.ui

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.NfcA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jh.uniteticketwriter.Config
import com.jh.uniteticketwriter.nfc.MikronCardManager
import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.extensions.toHexString
import kotlinx.android.synthetic.main.activity_waiting_for_tag.*
import java.io.IOException

class ReadTagActivity : AppCompatActivity() {

    private val LOGTAG = ReadTagActivity::class.java.simpleName
    lateinit var cardManager: MikronCardManager

    private fun readTag() {
        if (NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            try {
                val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
                cardManager = MikronCardManager()
                Config.currentManager = cardManager
                cardManager.connect(tag)

                Log.d(LOGTAG, "Read Card! \n Available ${cardManager.availableSizeBytes} bytes")
                Log.d(
                    LOGTAG,
                    "Card content: ${cardManager.readAllRaw().toTypedArray().toHexString(4)}"
                )

                tag_message.text = cardManager.readNdef().message.toString()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_for_tag)
        readTag()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        readTag()
    }
}
