package com.example.a25lifecyclelifecycleownerlifecycleobservercontext

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val audioPlayer = AudioPlayer()
    private val analyticsTracker = AnalyticsTracker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(audioPlayer)
        lifecycle.addObserver(analyticsTracker)

        UserTracker.register(this)
        Timber.d("Зареєстровано слухачів у UserTracker: ${UserTracker.getListenersCount()}")

        findViewById<Button>(R.id.btnOpenSecond)?.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        UserTracker.unregister(this)
        Timber.d("Після unregister залишилось слухачів: ${UserTracker.getListenersCount()}")
    }
}