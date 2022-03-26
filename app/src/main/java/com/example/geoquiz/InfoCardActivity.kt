package com.example.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import java.io.InputStreamReader

class InfoCardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_card)

        val countries = arrayListOf<Country>()

        val csvReader = CSVReaderBuilder(InputStreamReader(assets.open("countries.csv")))
            .withCSVParser(CSVParserBuilder().withSeparator(',').build())
            .build()
        var line: Array<String>? = csvReader.readNext()
        while (line != null) {
            countries.add(Country(line[0], line[1], line[2], line[3], line[4], line[5]))
            line = csvReader.readNext()
        }

        val country = intent.getStringExtra("country")
        val capital = ""
        val president = ""
        val primeMinister = ""
        val area = ""
        val population = ""

        countries.forEach(){
            if (it.country == country){
                val capital = it.capital
                val president = it.president
                val primeMinister = it.primeMinister
                val area = it.area
                val population = it.population
            }
        }

        val countryNameTextView = findViewById<TextView>(R.id.countryName)
        countryNameTextView.text = country.toString()

        val flagImage = country.toString().replace(" ", "_").lowercase()
        val imageResource = resources.getIdentifier("@drawable/$flagImage", null, packageName)
        val res = getDrawable(imageResource)
        val countryFlagView = findViewById<ImageView>(R.id.countryFlag)
        countryFlagView.setImageDrawable(res)

        val countryCapital = findViewById<TextView>(R.id.countryCapital)
        countryCapital.text = "Capital: $capital"

        val countryPresident = findViewById<TextView>(R.id.countryPresident)
        countryPresident.text = "President: $president"

        val countryPrimeMinister1 = findViewById<TextView>(R.id.countryPrimeMinister1)
        countryPrimeMinister1.text = "Prime Minister: $primeMinister"

        val countryPrimeMinister2 = findViewById<TextView>(R.id.countryPrimeMinister2)
        countryPrimeMinister2.text = "Prime Minister: $primeMinister"

        val countryArea = findViewById<TextView>(R.id.countryArea)
        countryArea.text = "Area: $area"

        val countryPopulation = findViewById<TextView>(R.id.countryPopulation)
        countryPopulation.text = "Population: $population"

    }

    fun goBack(view: View) {
        val intent = Intent(this, LearningModeActivity::class.java)
        startActivity(intent)
    }

}