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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_for_tag)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onResume() {
//        enableForegroundDispatch(this, NfcAdapter.getDefaultAdapter(this))
        super.onResume()
    }

    override fun onPause() {
//        disableForegroundDispatch(this, NfcAdapter.getDefaultAdapter(this))
        super.onPause()
    }
}
