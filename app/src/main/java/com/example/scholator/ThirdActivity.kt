package com.example.scholator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)

        // Handle system window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up Gender Spinner
        val genderSpinner: Spinner = findViewById(R.id.gender_spinner)
        val genderAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.gender_options, // Ensure you have defined this in strings.xml
            android.R.layout.simple_spinner_item
        )
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = genderAdapter

        // Set up Caste Spinner
        val casteSpinner: Spinner = findViewById(R.id.caste_spinner)
        val casteAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.caste_options, // Ensure you have defined this in strings.xml
            android.R.layout.simple_spinner_item
        )
        casteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        casteSpinner.adapter = casteAdapter

        // Set up Disability Spinner
        val disabilitySpinner: Spinner = findViewById(R.id.disability_spinner)
        val disabilityAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.disability_options, // Ensure you have defined this in strings.xml
            android.R.layout.simple_spinner_item
        )
        disabilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        disabilitySpinner.adapter = disabilityAdapter
    }
}
