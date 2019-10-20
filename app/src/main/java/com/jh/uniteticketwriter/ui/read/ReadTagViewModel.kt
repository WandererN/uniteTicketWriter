package com.jh.uniteticketwriter.ui.read

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import com.jh.uniteticketwriter.Config
import com.jh.uniteticketwriter.extensions.toHexString
import com.jh.uniteticketwriter.ui.ReadTagActivity

class ReadTagViewModel : ViewModel() {
    private var cardManager = Config.currentManager
    private val LOG_TAG = ReadTagActivity::class.java.simpleName

    fun readTag(receivedIntent: Intent?) {
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

                //     tag_message.text = cardManager.readNdef().message.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
