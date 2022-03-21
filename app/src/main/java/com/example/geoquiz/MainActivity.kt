package com.example.geoquiz


import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun selectLearningMode(view: View) {
        val intent = Intent(this, LearningModeActivity::class.java)
        startActivity(intent)
    }

    fun selectQuizMode(view: View) {
        val intent = Intent(this, QuizSelectionActivity::class.java)
        startActivity(intent)
    }

    fun selectLeaderBoard(view: View) {
        val intent = Intent(this, WorldLeaderboardActivity::class.java)
        startActivity(intent)
    }

    fun selectHighScore(view: View) {
        val intent = Intent(this, HighScoreActivity::class.java)
        startActivity(intent)
    }

}