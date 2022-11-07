package com.hassan.hanoykotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.hassan.hanoykotlin.databinding.ActivitySplashBinding
import com.hassan.hanoykotlin.preferences.PreferenceManager
import com.hassan.hanoykotlin.utils.Constants.Companion.SPLASH_DURATION
import com.hassan.hanoykotlin.utils.Constants.Companion.TEXT1
import com.hassan.hanoykotlin.utils.Constants.Companion.TEXT2
import com.hassan.hanoykotlin.utils.Constants.Companion.URL
import java.util.*
import java.util.concurrent.TimeUnit

class SplashActivity : BasicActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(binding.root)
    }

    override fun initValues() {
        super.initValues()
        val startTime = Date()

        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                this.onConfigFetched(startTime)
            } else {
                Toast.makeText(
                    this@SplashActivity,
                    "Something went wrong. Please try again later.",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        PreferenceManager.getInstance(this).saveOpenCount()
    }

    private fun onConfigFetched(startTime: Date) {
        val now = Date()

        val diffInSec = TimeUnit.MILLISECONDS.toSeconds(now.time - startTime.time)

        if (diffInSec < 1) {
            // Using cached data
            object : Thread() {
                override fun run() {
                    super.run()
                    try {
                        sleep(SPLASH_DURATION)
                        startNextActivity()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }.start()
        } else {
            PreferenceManager.getInstance(this@SplashActivity)
                .saveRemoteConfig(Firebase.remoteConfig.getString(TEXT2))
            startNextActivity()
        }
    }

    private fun startNextActivity() {
        if (Firebase.remoteConfig.getString(TEXT1) == "aaa") startActivity(
            Intent(
                this@SplashActivity,
                MainActivity::class.java
            )
        ) else startActivity(
            Intent(this@SplashActivity, WebViewActivity::class.java)
                .putExtra("url", Firebase.remoteConfig.getString(URL))
        )
        finish()
    }

}