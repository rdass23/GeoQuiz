package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class CountryQuizActivity : AppCompatActivity(), View.OnClickListener {


    val questions = arrayListOf<String>("What country capital is ", "What is the prime minister of")

    var correct = 0
    var currentQuestion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_quiz)

        val choiceA = findViewById<Button>(R.id.choiceA)
        val choiceB = findViewById<Button>(R.id.choiceB)
        val choiceC = findViewById<Button>(R.id.choiceC)
        val choiceD = findViewById<Button>(R.id.choiceD)

        choiceA.setOnClickListener(this)
        choiceB.setOnClickListener(this)
        choiceC.setOnClickListener(this)
        choiceD.setOnClickListener(this)

        startQuiz()
    }

    fun startQuiz() {

        correct = 0
        currentQuestion = 0

        setupQuestion()
    }

    fun setupQuestion() {

        val questionText = findViewById<TextView>(R.id.question)
        questionText.setText(questions[0])

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.choiceA -> println("Choice A")
            R.id.choiceB -> println("Choice B")
            R.id.choiceC -> println("Choice C")
            R.id.choiceD -> println("Choice D")
        }
    }


}