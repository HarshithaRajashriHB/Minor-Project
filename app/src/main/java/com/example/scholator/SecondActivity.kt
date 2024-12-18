package com.example.scholator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enabling the edge-to-edge layout
        setContentView(R.layout.activity_second)

        // Initialize the DatabaseHelper
        val dbHelper = DatabaseHelper(this)

        // Insert scholarship data only if the database is empty
        val scholarshipList = listOf(
            Scholarships(
                "Bharti Airtel Scholarship Program",
                "Bharti Airtel",
                "Varies",
                "<85 lakh>",
                "Meritorious students pursuing engineering",
                "This scholarship aims to provide financial support to academically excellent students pursuing engineering courses. It focuses on empowering underprivileged students to achieve their academic and career aspirations.",
                "https://bhartifoundation.org/bharti-airtel-scholarship/",                 // State
                "India",          // Annual Family Income
                "any",                  // Caste
                "Female",                   // Gender
                false                      // Disability
            ),

            Scholarships(
                "Aspire Scholarship Program",
                "SWAYAM Charitable Trust",
                "varies",
                "less than 6 lakh",  // Annual Family Income (if available)
                "Students joining the first-year of B.Tech. courses at any of the following 11 institutions can apply:\n" +
                        "Indian Institute of Technology Kharagpur (IIT-Kharagpur)\n" +
                        "Indian Institute of Technology Hyderabad (IIT-Hyderabad)\n" +
                        "Indian Institute of Technology Patna (IIT-Patna)\n" +
                        "Indian Institute of Technology Delhi (IIT-Delhi)\n" +
                        "Indian Institute of Technology Bombay (IIT-Bombay)\n" +
                        "Indian Institute of Technology Goa (IIT-Goa)\n" +
                        "Indian Institute of Information Technology, Design & Manufacturing (IIITDM) Jabalpur\n" +
                        "Indraprastha Institute of Information Technology Delhi (IIIT-Delhi)\n" +
                        "Dr Shyama Prasad Mukherjee International Institute of Information Technology Naya Raipur (IIIT NR)\n" +
                        "Jabalpur Engineering College\n" +
                        "National Institute of Advanced Manufacturing Technology (NIAMT, Ranchi)",
                " initiative of SWAYAM Charitable Trust that aims to provide financial support to underprivileged students pursuing B.Tech. courses at 11 prestigious engineering institutions across India",
                "https://services.india.gov.in/service/detail/aspire-scholarship-kerala",  // Link (if available)
                "kerala",  // State
                "any",    // Caste
                "any",    // Gender
                false      // Disability (true for students with disability)
            ),



            Scholarships(
                "Mohan T Advani Centennial Scholarship Programme",
                "Blue Star Foundation",
                "upto 75000",
                "Less than 2.5 LPA",
                "First and second-year students enrolled in a degree programme in architecture or engineering",
                "initiative of SWAYAM Charitable Trust that aims to provide financial support to underprivileged students pursuing B.Tech. courses at 11 prestigious engineering institutions across India.",
                "https://www.bluestarfoundation.in/",   // Example state
                "any",   // Example annual family income
                "any",   // Example caste
                "any",   // Example gender
                false   // Example disability status (No for no disability)
            ),

            Scholarships(
                "Legrand Empowering Scholarship Program",
                "Legrand",
                "upto 65000 pa",
                "any",          // Annual Family Income
                " Under this program, academically promising girl students pursuing B.Tech., B.E., B.Arch., or other undergraduate degrees in Finance or Science",          // Income
                "The Legrand Empowering Scholarship Program 2024-25, a Social Initiative of Group Legrand India, aims to provide financial support to meritorious girls, differently-abled girls, Covid-affected students, LGBTQ+ students and students with single parents or orphans for their higher education.",
                "https://legrandscholarship.co.in/",          // Link (empty string for now if no link is provided)
                "India",     // State
                "General",       // Caste
                "Girl",       // Gender
                false        // Disability
            ),
        Scholarships(
            "Ratan Tata Scholarship for Engineering Students",
            "Tata Group",
            "Varies",
            "upto 10000",          // Annual Family Income
            "Students pursuing various courses like Engineering, Medical, etc.",          // Income
            "Tata Scholarship for Engineering students is offered by the Tata Education and Development Trust to facilitate the higher education of students. Tata Scholarships are offered by various philanthropic units of the Tata group.",
            "https://www.tatatrusts.org/our-work/individual-grants-programme/education-grants",          // Link (empty string for now if no link is provided)
            "India",     // State
            "any",       // Caste
            "any",       // Gender
            false        // Disability
        )

        )

        // Check and insert scholarships if the database is empty
        if (dbHelper.getTopScholarships(3).isEmpty()) {
            scholarshipList.forEach { scholarship ->
                dbHelper.insertScholarship(scholarship)
            }
        }

        // Fetch the top 3 scholarships from the database
        val scholarshipListing = dbHelper.getTopScholarships(3)

        // Set up RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.scholarshipsRecyclerView)
        val adapter = ScholarshipAdapter(scholarshipListing) // Pass the fetched list to the adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Set up window insets for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the "Check Eligibility" button
        val checkEligibilityButton: Button = findViewById(R.id.check_eligibility)

        // Set OnClickListener to navigate to ThirdActivity
        checkEligibilityButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}
