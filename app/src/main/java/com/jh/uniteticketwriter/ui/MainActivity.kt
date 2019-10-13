package com.jh.uniteticketwriter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jh.uniteticketwriter.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    private fun startActivity(activity: Class<*>) {
        val activityToStart = Intent(this, activity)
        startActivity(activityToStart)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        upload_button.setOnClickListener {
            startActivity(UploadActivity::class.java)
        }
        download_button.setOnClickListener {
            startActivity(ReadTagActivity::class.java)
        }
        about_button.setOnClickListener {
            startActivity(AboutActivity::class.java)

        }
    }
}
