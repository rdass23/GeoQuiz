package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
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

    fun playAgain(view: View) {
        val gameType = intent.extras?.get("gametype")
        println(gameType)
        if (gameType == "flagquiz") {
            val intent = Intent(this, FlagQuizInfoActivity::class.java)
            startActivity(intent)
        } else if (gameType == "countryquiz") {
            val intent = Intent(this, MapQuizInfoActivity::class.java)
            startActivity(intent)
        }
    }

    fun backButtonResults(view: View) {
        val intent = Intent(this, QuizSelectionActivity::class.java)
        startActivity(intent)
    }

    fun showPopup(v: View) {

        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu, popup.menu)
        popup.show()
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.learn -> {
                    val intent = Intent(this@QuizResultsActivity, LearningModeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.quiz -> {
                    val intent = Intent(this@QuizResultsActivity, QuizSelectionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.lead -> {
                    val intent = Intent(this@QuizResultsActivity, WorldLeaderboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.score -> {
                    val intent = Intent(this@QuizResultsActivity, HighScoreActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

}