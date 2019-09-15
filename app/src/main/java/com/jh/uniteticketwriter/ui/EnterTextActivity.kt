package com.jh.uniteticketwriter.ui

import android.app.Activity
import android.os.Bundle
import com.jh.uniteticketwriter.Config
import com.jh.uniteticketwriter.NfcTextMessage
import com.jh.uniteticketwriter.R
import kotlinx.android.synthetic.main.activity_write_text.*

class EnterTextActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_text)
        write_text_tag_button.setOnClickListener {
            if (Config.currentManager != null)
                try {
                    Config.currentManager?.writeNdef(NfcTextMessage(tag_text_message.text.toString()))
                } catch (e: Exception) {
                    e.printStackTrace()
                    //TODO add alertDialog to show error
                }
        }
    }
}