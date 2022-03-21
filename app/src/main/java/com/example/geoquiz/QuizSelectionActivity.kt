package com.example.geoquiz

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class QuizSelectionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_selection)
    }

    fun goBack(view: View) {
        finish()
    }

    fun startCountryQuiz(view: View) {
        val intent = Intent(this, MapQuizInfoActivity::class.java)
        startActivity(intent)
    }

}