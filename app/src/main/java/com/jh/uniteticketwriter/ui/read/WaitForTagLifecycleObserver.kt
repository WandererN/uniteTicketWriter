package com.jh.uniteticketwriter.ui.read

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class WaitForTagLifecycleObserver(
    private val activity: FragmentActivity?,
    private val adapter: NfcAdapter?
) :
    LifecycleObserver {
    private fun enableForegroundDispatch(activity: FragmentActivity?, adapter: NfcAdapter?) {
        // here we are setting up receiving activity for a foreground dispatch
        // thus if activity is already started it will take precedence over any other activity or app
        // with the same intent filters
        if (activity == null)
            return
        val pendingIntent =
            PendingIntent.getActivity(activity.applicationContext, 0, activity.intent, 0)

        val techList = arrayOf(Array(1) { "android.nfc.tech.NfcA" })
        val filter = IntentFilter().apply {
            addAction(NfcAdapter.ACTION_TECH_DISCOVERED)
            addCategory(Intent.CATEGORY_DEFAULT)
        }

        adapter?.enableForegroundDispatch(activity, pendingIntent, arrayOf(filter), techList)
    }

    private fun disableForegroundDispatch(activity: FragmentActivity?, adapter: NfcAdapter?) {
        adapter?.disableForegroundDispatch(activity)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun enableForegroundDispatch() {
        enableForegroundDispatch(activity, adapter)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disableForegroundDispatch() {
        disableForegroundDispatch(activity, adapter)
    }
}