package com.example.geoquiz

import android.content.Intent
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

    fun playAgain() {
        val gameType = intent.extras?.get("gametype")

        if (gameType == "flagquiz") {
            val intent = Intent(this, FlagQuizActivity::class.java)
            startActivity(intent)
        } else if (gameType == "countryquiz") {
            val intent = Intent(this, CountryQuizActivity::class.java)
            startActivity(intent)
        }
    }

    fun backButton() {
        val intent = Intent(this, QuizSelectionActivity::class.java)
        startActivity(intent)
    }


}