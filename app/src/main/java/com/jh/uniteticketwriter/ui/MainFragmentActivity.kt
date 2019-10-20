package com.jh.uniteticketwriter.ui

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.ui.read.WaitForTagLifecycleObserver


class MainFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        lifecycle.addObserver(
            WaitForTagLifecycleObserver(
                this,
                NfcAdapter.getDefaultAdapter(applicationContext)
            )
        )
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment)
        val activeFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
        activeFragment?.let {
            if(it is IntentListenerFragment)
                it.onIntent(intent)
        }
    }
}
