package com.example.geoquiz

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu

class QuizSelectionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_selection)
    }

    fun goBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun startCountryQuiz(view: View) {
        val intent = Intent(this, MapQuizInfoActivity::class.java)
        startActivity(intent)
    }

    fun startFlagQuiz(view: View) {
        val intent = Intent(this, FlagQuizInfoActivity::class.java)
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
                    val intent = Intent(this@QuizSelectionActivity, LearningModeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.quiz -> {
                    val intent = Intent(this@QuizSelectionActivity, QuizSelectionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.lead -> {
                    val intent = Intent(this@QuizSelectionActivity, WorldLeaderboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.score -> {
                    val intent = Intent(this@QuizSelectionActivity, HighScoreActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

}