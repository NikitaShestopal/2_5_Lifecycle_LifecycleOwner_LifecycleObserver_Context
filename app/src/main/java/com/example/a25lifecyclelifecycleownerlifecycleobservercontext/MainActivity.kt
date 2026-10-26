package com.example.a25lifecyclelifecycleownerlifecycleobservercontext

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val audioPlayer = AudioPlayer()
    private val analyticsTracker = AnalyticsTracker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Підписка обзерверів на життєвий цикл
        lifecycle.addObserver(audioPlayer)
        lifecycle.addObserver(analyticsTracker)

        findViewById<Button>(R.id.btnOpenSecond)?.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}