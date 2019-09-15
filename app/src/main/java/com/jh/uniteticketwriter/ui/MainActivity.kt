package com.jh.uniteticketwriter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jh.uniteticketwriter.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        upload_button.setOnClickListener {
            val uploadActivity = Intent(this, UploadActivity::class.java)
            startActivity(uploadActivity)
        }
        download_button.setOnClickListener {
            val downloadActivity = Intent(this, ReadTagActivity::class.java)
            startActivity(downloadActivity)
        }
        about_button.setOnClickListener {
            val downloadActivity = Intent(this, AboutActivity::class.java)
            startActivity(downloadActivity)

        }
    }
}
