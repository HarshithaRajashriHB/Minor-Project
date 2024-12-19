package com.example.scholator

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FourthActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        // Initialize the database helper
        dbHelper = DatabaseHelper(this)

        // Retrieve data passed from ThirdActivity
        val gender = intent.getStringExtra("gender") ?: "Any"
        val caste = intent.getStringExtra("caste") ?: "Any"
        val disabilityString = intent.getStringExtra("disability") ?: "false"
        val state = intent.getStringExtra("state") ?: "Any"  // Default to "Any" if state is not passed

        val disability = disabilityString.toBoolean()

// Debugging: print values to check
        Log.d("FilteredScholarship", "Gender: $gender, Caste: $caste, Disability: $disability, State: $state")



        // Query the database for filtered scholarships
        val filteredScholarships = intent.getSerializableExtra("filteredScholarships") as? ArrayList<Scholarships> ?: emptyList()

        val listView: ListView = findViewById(R.id.scholarship_list)
        val noDataTextView: TextView = findViewById(R.id.no_data_text_view)

        if (filteredScholarships.isNotEmpty()) {
            // Set up the adapter to display the scholarships in the ListView
            val adapter = FilteredScholarshipAdapter(this, filteredScholarships)
            listView.adapter = adapter

            // Hide "No scholarships found" message
            noDataTextView.visibility = TextView.GONE
        } else {
            // Show "No scholarships found" message
            noDataTextView.visibility = TextView.VISIBLE
            listView.visibility = ListView.GONE
        }

    }
    }

