package com.jh.uniteticketwriter.ui

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jh.uniteticketwriter.Config
import com.jh.uniteticketwriter.nfc.MikronCardManager
import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.extensions.toHexString
import kotlinx.android.synthetic.main.activity_waiting_for_tag.*
import java.io.IOException
import java.lang.Exception

class ReadTagActivity : AppCompatActivity() {

    private val LOG_TAG = ReadTagActivity::class.java.simpleName
    private var cardManager = Config.currentManager

    private fun enableForegroundDispatch(activity: AppCompatActivity, adapter: NfcAdapter?) {

        // here we are setting up receiving activity for a foreground dispatch
        // thus if activity is already started it will take precedence over any other activity or app
        // with the same intent filters

        val pendingIntent = PendingIntent.getActivity(activity.applicationContext, 0, intent, 0)

        val techList = arrayOf(Array(1) { "android.nfc.tech.NfcA" })
        val filter = IntentFilter().apply {
            addAction(NfcAdapter.ACTION_TECH_DISCOVERED)
            addCategory(Intent.CATEGORY_DEFAULT)
        }

        adapter?.enableForegroundDispatch(activity, pendingIntent, arrayOf(filter), techList)
    }

    private fun disableForegroundDispatch(activity: AppCompatActivity, adapter: NfcAdapter?) {
        adapter?.disableForegroundDispatch(activity)
    }

    private fun readTag(receivedIntent: Intent?) {
        if (NfcAdapter.ACTION_TECH_DISCOVERED == receivedIntent?.action) {
            try {
                val tag = receivedIntent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)

                cardManager.disconnect()
                cardManager.connect(tag)

                Log.d(LOG_TAG, "Read Card! \n Available ${cardManager.availableSizeBytes} bytes")
                Log.d(
                    LOG_TAG,
                    "Card content: ${cardManager.readAllRaw().toTypedArray().toHexString(4)}"
                )

                tag_message.text = cardManager.readNdef().message.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_for_tag)
    }

    override fun onNewIntent(intent: Intent?) {
        readTag(intent)
        super.onNewIntent(intent)
    }

    override fun onResume() {
        enableForegroundDispatch(this, NfcAdapter.getDefaultAdapter(this))
        readTag(intent)
        super.onResume()
    }

    override fun onPause() {
        disableForegroundDispatch(this, NfcAdapter.getDefaultAdapter(this))
        super.onPause()
    }
}
