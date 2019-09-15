package com.jh.uniteticketwriter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jh.uniteticketwriter.R
import kotlinx.android.synthetic.main.activity_upload.*

class UploadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        write_text_button.setOnClickListener {
            val enterTextActivity = Intent(this, EnterTextActivity::class.java)
            startActivity(enterTextActivity)
        }
    }
}
