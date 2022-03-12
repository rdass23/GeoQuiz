package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MapQuizInfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_quiz_info)
    }

    fun goBack(view: View) {
        finish()
    }

    fun startQuiz(view: View) {
        val intent = Intent(this, CountryQuizActivity::class.java)
        startActivity(intent)
    }
}