package com.jh.uniteticketwriter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jh.uniteticketwriter.R

class MainFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
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
