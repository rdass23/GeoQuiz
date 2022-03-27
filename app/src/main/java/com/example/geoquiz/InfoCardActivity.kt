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
        var capital = ""
        var president = ""
        var primeMinister = ""
        var area = ""
        var population = ""

        countries.forEach(){
            if (it.country == country){
                capital = it.capital
                president = it.president
                primeMinister = it.primeMinister
                area = it.area
                population = it.population
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
        countryCapital.text = "CAPITAL: $capital"

        if (president.isNotEmpty() && primeMinister.isNullOrEmpty()){
            val countryPresident = findViewById<TextView>(R.id.countryPresident)
            countryPresident.text = "PRESIDENT: $president"

            val countryArea1 = findViewById<TextView>(R.id.countryArea1)
            countryArea1.text = "AREA: $area"

            val countryPopulation1 = findViewById<TextView>(R.id.countryPopulation1)
            countryPopulation1.text = "POPULATION: $population"
        }

        if (president.isNullOrEmpty() && primeMinister.isNotEmpty()){
            val countryPrimeMinister1 = findViewById<TextView>(R.id.countryPrimeMinister1)
            countryPrimeMinister1.text = "PRIME MINISTER: $primeMinister"

            val countryArea1 = findViewById<TextView>(R.id.countryArea1)
            countryArea1.text = "AREA: $area"

            val countryPopulation1 = findViewById<TextView>(R.id.countryPopulation1)
            countryPopulation1.text = "POPULATION: $population"
        }


        if (president.isNotEmpty() && primeMinister.isNotEmpty()) {
            val countryPresident = findViewById<TextView>(R.id.countryPresident)
            countryPresident.text = "PRESIDENT: $president"

            val countryPrimeMinister2 = findViewById<TextView>(R.id.countryPrimeMinister2)
            countryPrimeMinister2.text = "PRIME MINISTER: $primeMinister"

            val countryArea2 = findViewById<TextView>(R.id.countryArea2)
            countryArea2.text = "AREA: $area"

            val countryPopulation2 = findViewById<TextView>(R.id.countryPopulation2)
            countryPopulation2.text = "POPULATION: $population"
        }

    }

    fun goBack(view: View) {
        val intent = Intent(this, LearningModeActivity::class.java)
        startActivity(intent)
    }

}