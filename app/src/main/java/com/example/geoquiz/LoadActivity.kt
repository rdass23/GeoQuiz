package com.example.geoquiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.animation.ObjectAnimator
import android.widget.ProgressBar

class LoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        val progressbar = findViewById<ProgressBar>(R.id.progressBar)
        progressbar.max = 100
        val currentProgress = 100
        ObjectAnimator.ofInt(progressbar, "progress", currentProgress)
            .setDuration(2000)
            .start()

    }
}