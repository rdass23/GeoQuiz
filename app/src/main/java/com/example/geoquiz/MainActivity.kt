package com.example.geoquiz


import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "You can't do that", Toast.LENGTH_SHORT).show()
    }

    fun selectLearningMode(view: View) {
        val intent = Intent(this, LearningModeActivity::class.java)
        startActivity(intent)
    }

    fun selectQuizMode(view: View) {
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
                    val intent = Intent(this@MainActivity, LearningModeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.quiz -> {
                    val intent = Intent(this@MainActivity, QuizSelectionActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.lead -> {
                    val intent = Intent(this@MainActivity, WorldLeaderboardActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.score -> {
                    val intent = Intent(this@MainActivity, HighScoreActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }


}