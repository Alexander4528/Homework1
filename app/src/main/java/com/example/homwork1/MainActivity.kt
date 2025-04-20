package com.example.homwork1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random
import kotlin.math.exp
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var meanVal: EditText // используем lateinit для инициализации позже
    private lateinit var varianceValue: EditText
    private lateinit var randomNumberResult: TextView
    private var savedRandomNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        meanVal = findViewById(R.id.mean_val)
        varianceValue = findViewById(R.id.variance_value)
        randomNumberResult = findViewById(R.id.random_number_result)
        val getRandomNum = findViewById<Button>(R.id.get_random_num)

        if (savedInstanceState != null) {
            savedRandomNumber = savedInstanceState.getString("randomNumber")
            // Убедитесь, что savedRandomNumber не равен null
            randomNumberResult.text = savedRandomNumber ?: "Ничего не сгенерировано"
        }

        getRandomNum.setOnClickListener {
            val mean = meanVal.text.toString().toDoubleOrNull() ?: 0.0 // безопасное преобразование
            val variance = varianceValue.text.toString().toDoubleOrNull() ?: 0.0 // безопасное преобразование
            val sigma = sqrt(variance)
            val randomNum = generateLogNormal(mean, sigma)
            randomNumberResult.text = randomNum.toString()
            savedRandomNumber = randomNum.toString() // сохранить число
        }
    }

    private fun generateLogNormal(mean: Double, sigma: Double): Double {
        val random = Random()
        val normalRandom = mean + sigma * random.nextGaussian()
        return exp(normalRandom)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("randomNumber", savedRandomNumber)
    }
}