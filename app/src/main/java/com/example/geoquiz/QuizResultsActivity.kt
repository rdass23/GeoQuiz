package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class QuizResultsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_results)

        val extras = intent.extras
        if (extras != null) {
            val score = extras.get("score")
            val scoreView = findViewById<TextView>(R.id.score)
            scoreView.setText(score.toString() + "/10")
        }

    }


}