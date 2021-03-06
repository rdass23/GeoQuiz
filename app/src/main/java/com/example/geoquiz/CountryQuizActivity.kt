package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import java.io.InputStreamReader

class CountryQuizActivity : AppCompatActivity(), View.OnClickListener {

    val questions = arrayListOf<String>("What country capital is ", "Which country has the prime minister named ", "Which country has the president named ")

    private lateinit var choiceA: Button
    private lateinit var choiceB: Button
    private lateinit var choiceC: Button
    private lateinit var choiceD: Button

    private lateinit var correctA: ImageView
    private lateinit var correctB: ImageView
    private lateinit var correctC: ImageView
    private lateinit var correctD: ImageView

    private lateinit var wrongA: ImageView
    private lateinit var wrongB: ImageView
    private lateinit var wrongC: ImageView
    private lateinit var wrongD: ImageView

    private var countries = arrayListOf<Country>()
    private var correct = 0
    private var currentQuestion = 0

    private var correctAnswer = Choice.A

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_quiz)

        choiceA = findViewById<Button>(R.id.choiceA)
        choiceB = findViewById<Button>(R.id.choiceB)
        choiceC = findViewById<Button>(R.id.choiceC)
        choiceD = findViewById<Button>(R.id.choiceD)

        correctA = findViewById<ImageView>(R.id.correctCA)
        correctB = findViewById<ImageView>(R.id.correctCB)
        correctC = findViewById<ImageView>(R.id.correctCC)
        correctD = findViewById<ImageView>(R.id.correctCD)

        wrongA = findViewById<ImageView>(R.id.wrongCA)
        wrongB = findViewById<ImageView>(R.id.wrongCB)
        wrongC = findViewById<ImageView>(R.id.wrongCC)
        wrongD = findViewById<ImageView>(R.id.wrongCD)

        choiceA.setOnClickListener(this)
        choiceB.setOnClickListener(this)
        choiceC.setOnClickListener(this)
        choiceD.setOnClickListener(this)

        val csvReader = CSVReaderBuilder(InputStreamReader(assets.open("countries.csv")))
            .withCSVParser(CSVParserBuilder().withSeparator(',').build())
            .build()
        var line: Array<String>? = csvReader.readNext()
        while (line != null) {
            countries.add(Country(line[0], line[1], line[2], line[3], line[4], line[5]))
            line = csvReader.readNext()
        }

        startQuiz()
    }

    fun startQuiz() {

        correct = 0
        currentQuestion = 0

        runQuiz()
    }

    fun runQuiz() {
        currentQuestion += 1
        if (currentQuestion > 10) {
            val intent = Intent(this, QuizResultsActivity::class.java)
            intent.putExtra("score", correct)
            intent.putExtra("gametype", "countryquiz")
            startActivity(intent)
        } else {
            hideAllImages()
            setupQuestion()
        }
    }

    fun hideAllImages() {
        correctA.visibility = View.INVISIBLE
        correctB.visibility = View.INVISIBLE
        correctC.visibility = View.INVISIBLE
        correctD.visibility = View.INVISIBLE
        wrongA.visibility = View.INVISIBLE
        wrongB.visibility = View.INVISIBLE
        wrongC.visibility = View.INVISIBLE
        wrongD.visibility = View.INVISIBLE
    }

    fun setupQuestion() {

        val questionText = findViewById<TextView>(R.id.question)
        questionText.gravity =  Gravity.CENTER

        val questionNumber = (0 until questions.size).random()

        // Correct Answer is the index of the correct country
        val correctAns = randomCorrectAnswer(questionNumber)

        val correctChoice = (0 until 4).random()

        if (questionNumber == 0) {
            questionText.setText(questions[questionNumber] + countries[correctAns].capital + "?")
        } else if (questionNumber == 1) {
            questionText.setText(questions[questionNumber] + countries[correctAns].primeMinister + "?")
        } else if (questionNumber == 2) {
            questionText.setText(questions[questionNumber] + countries[correctAns].president + "?")
        }

        val wrongAnswers = randomWrongAnswer(questionNumber, correctAns)

        choiceA.setText(countries[wrongAnswers[0]].country)
        choiceB.setText(countries[wrongAnswers[1]].country)
        choiceC.setText(countries[wrongAnswers[2]].country)
        choiceD.setText(countries[wrongAnswers[3]].country)

        if (correctChoice == 0) {
            correctAnswer = Choice.A
            choiceA.setText(countries[correctAns].country)
        } else if (correctChoice == 1) {
            correctAnswer = Choice.B
            choiceB.setText(countries[correctAns].country)
        }else if (correctChoice == 2) {
            correctAnswer = Choice.C
            choiceC.setText(countries[correctAns].country)
        } else {
            correctAnswer = Choice.D
            choiceD.setText(countries[correctAns].country)
        }

    }

    fun randomCorrectAnswer(questionNumber: Int): Int {
        while (true) {
            val rdn = (0 until countries.size).random()
            if (questionNumber == 0) {
                if (countries[rdn].capital != "") {
                    return rdn
                }
            } else if (questionNumber == 1) {
                if (countries[rdn].primeMinister != "") {
                    return rdn
                }
            }else if (questionNumber == 2) {
                if (countries[rdn].president != "") {
                    return rdn
                }
            }
        }
    }

    fun randomWrongAnswer(questionNumber: Int, correctAns: Int): List<Int> {
        var retWrong = arrayListOf<Int>()
        var x = 0
        while (x < 4) {
            while (true) {
                val rdn = (0 until countries.size).random()
                if (questionNumber == 0) {
                    if (countries[rdn].capital != "" && correctAns != rdn && !retWrong.contains(rdn)) {
                        x += 1
                        retWrong.add(rdn)
                        break
                    }
                } else if (questionNumber == 1) {
                    if (countries[rdn].primeMinister != "" && correctAns != rdn && !retWrong.contains(rdn)) {
                        x += 1
                        retWrong.add(rdn)
                        break
                    }
                } else if (questionNumber == 2) {
                    if (countries[rdn].president != "" && correctAns != rdn && !retWrong.contains(rdn)) {
                        x += 1
                        retWrong.add(rdn)
                        break
                    }
                }
            }
        }
        return retWrong
    }

    fun showCorrectImages(correct: Choice) {
        wrongA.visibility = View.VISIBLE
        wrongB.visibility = View.VISIBLE
        wrongC.visibility = View.VISIBLE
        wrongD.visibility = View.VISIBLE
        if (correct == Choice.A) {
            wrongA.visibility = View.INVISIBLE
            correctA.visibility = View.VISIBLE
        } else if (correct == Choice.B) {
            wrongB.visibility = View.INVISIBLE
            correctB.visibility = View.VISIBLE
        } else if (correct == Choice.C) {
            wrongC.visibility = View.INVISIBLE
            correctC.visibility = View.VISIBLE
        } else if (correct == Choice.D) {
            wrongD.visibility = View.INVISIBLE
            correctD.visibility = View.VISIBLE
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.choiceA -> {
                if (correctAnswer == Choice.A) {
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                    correct += 1
                } else {
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.choiceB -> {
                if (correctAnswer == Choice.B) {
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                    correct += 1
                } else {
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.choiceC -> {
                if (correctAnswer == Choice.C) {
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                    correct += 1
                } else {
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.choiceD -> {
                if (correctAnswer == Choice.D) {
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                    correct += 1
                } else {
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
            }
        }
        showCorrectImages(correctAnswer)
        Handler().postDelayed(this::runQuiz, 3000)
    }

    fun exitQuiz(view: View) {
        val intent = Intent(this, QuizSelectionActivity::class.java)
        startActivity(intent)
    }

    enum class Choice {
        A, B, C, D
    }

}