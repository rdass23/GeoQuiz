package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MapQuizInfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_quiz_info)
    }

    fun goBack() {
        finish()
    }
}