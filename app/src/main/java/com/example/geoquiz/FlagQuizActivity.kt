package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import java.io.InputStreamReader

class FlagQuizActivity : AppCompatActivity(), View.OnClickListener {

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

    private var correctAnswer = CountryQuizActivity.Choice.A


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flag_quiz)

        choiceA = findViewById<Button>(R.id.choiceAFlag)
        choiceB = findViewById<Button>(R.id.choiceBFlag)
        choiceC = findViewById<Button>(R.id.choiceCFlag)
        choiceD = findViewById<Button>(R.id.choiceDFlag)

        correctA = findViewById<ImageView>(R.id.correctFA)
        correctB = findViewById<ImageView>(R.id.correctFB)
        correctC = findViewById<ImageView>(R.id.correctFC)
        correctD = findViewById<ImageView>(R.id.correctFD)

        wrongA = findViewById<ImageView>(R.id.wrongFA)
        wrongB = findViewById<ImageView>(R.id.wrongFB)
        wrongC = findViewById<ImageView>(R.id.wrongFC)
        wrongD = findViewById<ImageView>(R.id.wrongFD)

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
            intent.putExtra("gametype", "flagquiz")
            intent.putExtra("score", correct)
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

        val flagView = findViewById<ImageView>(R.id.flagView)
        val correctAns = randomCorrectAnswer(flagView)

        val correctChoice = (0 until 4).random()

        val wrongAnswers = randomWrongAnswer(correctAns)

        choiceA.setText(countries[wrongAnswers[0]].country)
        choiceB.setText(countries[wrongAnswers[1]].country)
        choiceC.setText(countries[wrongAnswers[2]].country)
        choiceD.setText(countries[wrongAnswers[3]].country)

        if (correctChoice == 0) {
            correctAnswer = CountryQuizActivity.Choice.A
            choiceA.setText(countries[correctAns].country)
        } else if (correctChoice == 1) {
            correctAnswer = CountryQuizActivity.Choice.B
            choiceB.setText(countries[correctAns].country)
        }else if (correctChoice == 2) {
            correctAnswer = CountryQuizActivity.Choice.C
            choiceC.setText(countries[correctAns].country)
        } else {
            correctAnswer = CountryQuizActivity.Choice.D
            choiceD.setText(countries[correctAns].country)
        }

    }

    fun randomCorrectAnswer(flagView: ImageView): Int {
        while (true) {
            val rdn = (0 until countries.size).random()
            val id = flagView.context.resources.getIdentifier(countries[rdn].country.lowercase(), "drawable", flagView.context.packageName)
            if (id != 0) {
                flagView.setImageResource(id)
                return rdn
            }
        }
    }

    fun randomWrongAnswer(correctAns: Int): List<Int> {
        var retWrong = arrayListOf<Int>()
        var x = 0
        while (x < 4) {
            while (true) {
                val rdn = (0 until countries.size).random()
                if (correctAns != rdn && !retWrong.contains(rdn)) {
                    x += 1
                    retWrong.add(rdn)
                    break
                }
            }
        }
        return retWrong
    }

    fun showCorrectImages(correct: CountryQuizActivity.Choice) {
        wrongA.visibility = View.VISIBLE
        wrongB.visibility = View.VISIBLE
        wrongC.visibility = View.VISIBLE
        wrongD.visibility = View.VISIBLE
        if (correct == CountryQuizActivity.Choice.A) {
            wrongA.visibility = View.INVISIBLE
            correctA.visibility = View.VISIBLE
        } else if (correct == CountryQuizActivity.Choice.B) {
            wrongB.visibility = View.INVISIBLE
            correctB.visibility = View.VISIBLE
        } else if (correct == CountryQuizActivity.Choice.C) {
            wrongC.visibility = View.INVISIBLE
            correctC.visibility = View.VISIBLE
        } else if (correct == CountryQuizActivity.Choice.D) {
            wrongD.visibility = View.INVISIBLE
            correctD.visibility = View.VISIBLE
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.choiceAFlag ->
                if (correctAnswer == CountryQuizActivity.Choice.A) {
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                    correct += 1
                } else {
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
            R.id.choiceBFlag ->
                if (correctAnswer == CountryQuizActivity.Choice.B) {
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                    correct += 1
                } else {
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
            R.id.choiceCFlag ->
                if (correctAnswer == CountryQuizActivity.Choice.C) {
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                    correct += 1
                } else {
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
            R.id.choiceDFlag ->
                if (correctAnswer == CountryQuizActivity.Choice.D) {
                    Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                    correct += 1
                } else {
                    Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
                }
        }
        showCorrectImages(correctAnswer)
        Handler().postDelayed(this::runQuiz, 3000)
    }

    fun exitQuiz(view: View) {
        val intent = Intent(this, QuizSelectionActivity::class.java)
        startActivity(intent)
    }

}