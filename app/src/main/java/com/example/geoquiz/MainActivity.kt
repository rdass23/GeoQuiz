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
    }


}