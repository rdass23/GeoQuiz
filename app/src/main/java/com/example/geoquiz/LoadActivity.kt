package com.example.geoquiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar

class LoadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)
        val bar = findViewById<ProgressBar>(R.id.progressBar)
        
        bar.max = 100
        val animation = ObjectAnimator.ofInt(bar, "progress", 100)
        animation.duration = 2000
        animation.start()

        Handler(Looper.getMainLooper()).postDelayed({
            // TODO: check for username
            if (usernameIsSet()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }, 2000)
    }

    private fun usernameIsSet(): Boolean {
        return false
    }
}