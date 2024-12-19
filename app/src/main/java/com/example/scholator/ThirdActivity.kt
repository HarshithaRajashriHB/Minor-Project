package com.example.scholator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        // Initialize DatabaseHelper
        dbHelper = DatabaseHelper(this)

        // Initialize UI components
        val stateEditText: EditText = findViewById(R.id.state_edit_text)
        val genderEditText: EditText = findViewById(R.id.gender_edit_text)
        val casteEditText: EditText = findViewById(R.id.caste_edit_text)
        val disabilityEditText: EditText = findViewById(R.id.disability_edit_text)
        val amountEditText: EditText = findViewById(R.id.amount_edit_text)

        val filterButton: Button = findViewById(R.id.filter_button)

        // Set filter button action
        filterButton.setOnClickListener {
            // Get values from EditTexts
            val state = stateEditText.text.toString().trim()
            val gender = genderEditText.text.toString().trim()
            val caste = casteEditText.text.toString().trim()
            val disability = disabilityEditText.text.toString().trim().toBoolean()
            val amount =
                amountEditText.text.toString().toIntOrNull() ?: -1 // Default to -1 if empty

            // Handle filtering
            if (state.isNotEmpty()) {
                val filteredScholarships =
                    dbHelper.getFilteredScholarships(state, gender, caste, disability)

                if (filteredScholarships != null && filteredScholarships.isNotEmpty()) {
                    // Create an intent to navigate to the next activity
                    val intent = Intent(this, FourthActivity::class.java)

                    // Pass the filteredScholarships list as a Serializable extra
                    intent.putExtra(
                        "filteredScholarships",
                        ArrayList(filteredScholarships)
                    )  // Using putExtra

                    startActivity(intent)
                } else {
                    // Handle the case when no scholarships are found
                    Toast.makeText(
                        this,
                        "No scholarships found matching the criteria",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        }
    }
}
