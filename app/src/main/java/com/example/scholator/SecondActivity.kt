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
                25000,
                850000,
                false,
                "This scholarship aims to provide financial support to academically excellent students pursuing engineering courses. It focuses on empowering underprivileged students to achieve their academic and career aspirations.",
                "https://bhartifoundation.org/bharti-airtel-scholarship/",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "Female",                   // Gender
                true                      // Disability
            ),
            Scholarships(
                "Aspire Scholarship Program",
                "SWAYAM Charitable Trust",
                30000,
                600000,  // Annual Family Income (if available)
                false,
                " initiative of SWAYAM Charitable Trust that aims to provide financial support to underprivileged students pursuing B.Tech. courses at 11 prestigious engineering institutions across India",
                "https://services.india.gov.in/service/detail/aspire-scholarship-kerala",  // Link (if available)
                "kerala",  // State
                "any",    // Caste
                "any",    // Gender
                true     // Disability (true for students with disability)
            ),
            Scholarships(
                "Mohan T Advani Centennial Scholarship Programme",
                "Blue Star Foundation",
                75000,
                250000,
                false,
                "initiative of SWAYAM Charitable Trust that aims to provide financial support to underprivileged students pursuing B.Tech. courses at 11 prestigious engineering institutions across India.",
                "https://www.bluestarfoundation.in/",   // Example state
                "any",   // Example annual family income
                "any",   // Example caste
                "any",   // Example gender
                true   // Example disability status (No for no disability)
            ),

            Scholarships(
                "Legrand Empowering Scholarship Program",
                "Legrand",
                65000,
                -1,          // Annual Family Income
               false ,          // Income
                "The Legrand Empowering Scholarship Program 2024-25, a Social Initiative of Group Legrand India, aims to provide financial support to meritorious girls, differently-abled girls, Covid-affected students, LGBTQ+ students and students with single parents or orphans for their higher education.",
                "https://legrandscholarship.co.in/",          // Link (empty string for now if no link is provided)
                "any",     // State
                "General",       // Caste
                "Female",       // Gender
                true        // Disability
            ),
        Scholarships(
            "Ratan Tata Scholarship for Engineering Students",
            "Tata Group",
            30000,
            100000,          // Annual Family Income
            false,          // Income
            "Tata Scholarship for Engineering students is offered by the Tata Education and Development Trust to facilitate the higher education of students. Tata Scholarships are offered by various philanthropic units of the Tata group.",
            "https://www.tatatrusts.org/our-work/individual-grants-programme/education-grants",          // Link (empty string for now if no link is provided)
            "any",     // State
            "any",       // Caste
            "any",       // Gender
            true        // Disability
        ) ,
            Scholarships(
                "Sitaram jindal",
            "Sitaram jindal Foundation",
            3200,
            -1,
            true,
            "Sitaram Jindal Foundation Scholarship Scheme 2024 is an initiative by the Sitaram Jindal Foundation (NGO), for students from Class 11 to the postgraduate level. It aims to provide financial assistance to underprivileged students pursuing various degree/diploma courses. The selected scholars will receive up to ₹3,200 per month.",
            "https://www.sitaramjindalfoundation.org/scholarships-for-students-in-bangalore.php",                 // State
            "any",          // Annual Family Income
            "any",                  // Caste
            "any",                   // Gender
            true                      // Disability
        ),
            Scholarships(
                "UGAM-Legrand",
                "Legrand India",
                60000,
                -1,
                true,
                "The Legrand Empowering Scholarship Program 2024-25, a Social Initiative of Group Legrand India, aims to provide financial support to meritorious girls, differently-abled girls, Covid-affected students, LGBTQ+ students and students with single parents or orphans for their higher education. Under this program, academically promising girl students pursuing B.Tech., B.E., B.Arch., or other undergraduate degrees in Finance or Science (such as B.Sc., B.Com., B.B.A., etc.) will receive 60% of the annual course fee, up to INR 60,000 per year. Students in the special category* will receive 80% of the annual course fee, up to INR 1,00,000 per year, until they complete their course, based on their academic performance.",
                "https://legrandscholarship.co.in/",                 // State
                "any",          // Annual Family Income
                "general",                  // Caste
                "female",                   // Gender
                true                      // Disability
            ),
            Scholarships(
                "Tata realty scholarship",
                "Tata Reality and infrastructure limited",
                100000,
                500000,
                true,
                "Tata  Realty and Infrastructure Limited organization has launched the Tata Reality Scholarship to provide financial help to continue their  Education. Under this scholarship, monetary help will be provided to the female students who are pursuing BE, BTech, and Bachelor of Architecture courses. With the help of this scholarship, the student will be able to meet academic expenses without worrying about any financial hurdles. The student who clears the eligibility criteria can easily apply under this scheme by visiting the official website.",
                "https://scholarshiplearn.com/tata-realty-scholarship-for-girls/",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "female",                   // Gender
                false                     // Disability
            ),
            Scholarships(
                "Prerana scholarship",
                "sc and st development and minority and other backward classes welfare department government of odisha ",
                1200,
                250000,
                false,
                "The government provides scholarships to deserving and economically disadvantaged students who cannot pay for their studies. Every year, the Odisha government announces various state award programs for every type of student, including general, minority, OBC, SC, and ST. Since they are not compelled to repay the scholarship money, the government provides them with financial assistance for their upcoming exams. Thus, those students awaiting the scholarship program can now apply for it. In addition to offering scholarships to students from underprivileged socioeconomic backgrounds, Prerana Scholarship Odisha provides employability skills to help students prepare for the workforce following graduation.",
                "https://scholarship.odisha.gov.in/website/news-details/eyJpdiI6IkdZVTdiVHRBVXhoUzJlS0Jna2tyTUE9PSIsInZhbHVlIjoiVXRUdlE4NThSV1RIZytoWU9RQjc3QT09IiwibWFjIjoiZWMwNTk5MjYyNDVkOGQxNzk3OGQ3MzkyYWY2OGNlNGM1MDZkODQ4NDY3ZjhjY2FhMjg5M2IyMWI5ZTI5MzkwYyJ9",                 // State
                "odisha",          // Annual Family Income
                "sc and st",                  // Caste
                "any",                   // Gender
                false                     // Disability
            ),
            Scholarships(
                "Pragati scholarship",
                "All India Council For Technical Education",
                50000,
                800000,
                true,
                "Pragati Scholarship is a government initiative implemented by the All India Council for Technical Education (AICTE). This program awards 5,000 scholarships annually to meritorious female students pursuing technical education. Each recipient receives ₹50,000 per year to support their studies.",
                "http://www.aicte-pragati-saksham-gov.in/login.php?r=session_invalid",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "female",                   // Gender
                false                     // Disability
            ),
            Scholarships(
                "Kotak Kanya Scholarship 2024-25",
                "Kotak Mahindra Group companies and Kotak Education Foundation",
                150000,
                600000,
                true,
                "Kotak Kanya Scholarship is a collaborative CSR Project of Kotak Mahindra Group companies and Kotak Education Foundation to promote education and livelihood among economically disadvantaged sections of society. This scholarship aims to offer financial assistance to meritorious girl students from low-income families to empower them to pursue higher studies in professional education after Class 12. \n" +
                        "\n" +
                        "Under Kotak Kanya Scholarship 2024-25, girl students who have passed Class 12 and aspire to pursue professional graduation courses like Engineering, MBBS, BDS, Integrated LLB (5 Years), B. Pharmacy, B.Sc. Nursing, Integrated BS-MS/BS-Research, in ISER, IISC (Bangalore), or other professional courses (Design, Architecture, etc.) from institutes of repute (NAAC/NIRF accredited) will be provided scholarship of INR 1.5 lakh* per year to pay their educational expenses until graduation (degree).",
                "https://www.kotakeducationfoundation.org/",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "female",                   // Gender
                true                     // Disability
            ),
            Scholarships(
                "Federal Bank Hormis Memorial Foundation Scholarship 2024-25",
                "Federal Bank Hormis Memorial Foundation ",
                100000,
                300000,
                false,
                "Federal Bank Hormis Memorial Foundation Scholarship 2024-25 is an initiative of Federal Bank to provide financial support to first-year undergraduate students pursuing specified professional courses. This scholarship aims to support the education of meritorious students from Gujarat, Karnataka, Kerala, Maharashtra, Punjab, and Tamil Nadu. The selected students will receive 100% reimbursement of tuition fees and other educational expenses, as per the college's fee structure.",
                "https://www.federalbank.co.in/fedbank-hormis-memorial-foundation",                 // State
                "gujarat, Karnataka, Kerala, Maharashtra, Punjab, and Tamil Nadu.",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                true                     // Disability
            ),
            Scholarships(
                "L'Oréal India For Young Women In Science Scholarships 2024-25",
                "L'Oréal India",
                250000,
                600000,
                true,
                "L'Oréal India invites applications for the L'Oréal India for Young Women in Science Scholarships 2024-25 from young women who have passed Class 12 and wish to pursue higher education in science. The scholarships will be awarded to promising young women from economically disadvantaged backgrounds. Selected scholars will receive financial aid to cover college fees for UG, PG, and PhD in any scientific field, including Pure Sciences, Life Sciences, Applied Sciences, Engineering, Medicine, Cosmetology, Cosmetic Science, Pharmacology, and more, at a recognized college or university in India.\n" +
                        "\n" +
                        "L'Oréal India offers this scholarship to support and encourage young women to pursue higher education in science. Established in 2003, the program has consistently helped young women pursue studies in various scientific fields",
                "https://www.loreal.com/en/india/articles/commitments/the-india-for-young-women-in-science-scholarship-programme/",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "female",                   // Gender
                false                     // Disability
            ),
            Scholarships(
                "LIC Golden Jubilee Scholarship 2024-25",
                "Life Insurance cooperation India ",
                20000,
                250000,
                false,
                "LIC Golden Jubilee Scholarship 2024-25 is an opportunity offered by the Life Insurance Corporation of India*, to students who have passed Class 10, 12 passed and  students from economically weaker sections who wish to pursue higher studies. The selected candidates will receive up to ₹40,000 per annum.\n" +
                        "\n" +
                        "*Life Insurance Corporation of India (LIC) is a public sector life insurance company that is owned by the Government of India. It was established with an objective of promoting education, health, relief of poverty or distress and advancement of other objects of general public utility.",
                "https://licindia.in/golden-jubilee-scholarship-scheme",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                false                     // Disability
            ),
            Scholarships(
                "HDFC Bank Parivartan's ECSS Programme 2024-25",
                "HDFC bank ",
                50000,
                250000,
                true,
                "HDFC Bank Parivartan's ECSS Programme 2024-25 is an initiative of HDFC Bank that aims to support meritorious and needy students belonging to underprivileged sections of society. The scholarship programme is meant for school students from Classes 1 to 12 and those pursuing diploma, ITI, polytechnic, UG, and PG (general and professional) programmes. Under the ECSS programme, the students who are unable to bear the cost of education due to personal/family crises or any other financial problems and are at risk of dropping out are provided with monetary assistance of up to INR 75,000 for their studies.",
                "https://www.hdfcbankecss.com/",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                false                     // Disability
            ),
            Scholarships(
                "North South Foundation (NSF) Scholarship",
                "North south foundation",
                30000,
                200000,
                true,
                "This scholarship programme is aimed towards economically backward students in India who have obtained merit based admission. We are not entertaining any course other than medicine, engineering and Polytechnic (3-year diploma).",
                "https://www.northsouth.org",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                true                    // Disability
            ),
            Scholarships(
                "Central Sector Scheme of Scholarship (CSSS)",
                "India government",
                12000,
                450000,
                true,
                "scholarship program provided by the Indian government, officially called the \"Pradhan Mantri Uchchatar Shiksha Protsahan (PM-USP) Central Sector Scheme of Scholarship for College and University Students\", aimed at financially supporting meritorious students from economically weaker sections pursuing higher education in colleges and universities across India; essentially providing financial assistance to help cover a part of their daily expenses while studying.",
                "http://www.scholarships.gov.in",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                true                    // Disability
            ),
            Scholarships(
                "G. P. BIRLA SCHOLARSHIP",
                "G.P BIRLA FOUNDATION",
                50000,
                300000,
                true,
                "The Trust has been established to carry forward the cherished dream of Late Shri G. P. Birla, the renowned Industrialist and Philanthropist, to promote education, develop human resources and empower the youth of the Country. With this objective, the Trust proposes to grant Scholarships to meritorious students, male or female, from West Bengal, who have in the year 2024, passed class XII examination and have secured 85% marks or more from WBCHSE or 90% or more from ISC/CBSE and whose family income is less than Rs. 3,00,000/- per year and as such are in need of financial help to pursue further studies. For students who are extra meritorius, maximum family income criteria may be relaxed in the specific cases at the discretion of the Trustees.",
                "https://www.gpbirlaedufoundation.com",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                true                    // Disability
            ),
            Scholarships(
                "Inspire Scholarship for Higher Education(SHE)",
                "Department of science and technology",
                80000,
                -1,
                true,
                "Scholarship for Higher Education (SHE) is a component of“Innovation in Science Pursuit for Inspired Research (INSPIRE)”, which is a flagship programme of the Department of Science and Technology (DST), under the Ministry of Science and Technology, Government of India. It aims to attract students to study basic and natural Science subjects at undergraduate and postgraduate levels and pursue research as a career by providing scholarships to interested and deserving students.Under SHE component, 12,000 scholarships (From SHE-2017 batch level onwards), each valued at ` 80,000/- are announced annually to students pursuing Bachelors and Masters courses in Basic and Natural Science areas. ",
                "https://www.gpbirlaedufoundation.com",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                true                    // Disability
            ),
            Scholarships(
                "AICTE - Saksham Scholarship Scheme",
                "AICTE",
                50000,
                800000,
                true,
                "Saksham is a Scholarship Scheme by the Ministry of Education, implemented by AICTE, aimed at providing encouragement and support to specially-abled children to pursue technical education. This is an attempt to give every young student, who is otherwise specially abled, the opportunity to study further and prepare for a successful future through technical education/ knowledge. ₹50,000/- per annum for every year of study i.e. maximum 4 years for the first year admitted students and maximum 3 years for the second year admitted students through lateral entry as lump sum amount towards payment of college fee, purchase of computer, stationeries, books, equipment, software, etc. The candidate should be admitted to the First year of the Degree level course OR the Second year of the Degree level course through lateral entry in any of the AICTE-approved institutions of the respective year. Family income from all sources should not be more than Rs. 8 lakh per annum during the current financial year.",
                "http://www.aicte-pragati-saksham-gov.in/",                 // State
                "any",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                true                    // Disability
            ),
            Scholarships(
                "NHFDC Trust Fund Scholarship for Differently-Abled Students",
                "National Handicapped Finance and Development Corporation",
                10000,
                25000,
                true,
                "The NHFDC Scholarship for Students with Disabilities is designed to empower eligible Indian students with disabilities to pursue their educational dreams. This scholarship program, initiated by the National Handicapped Finance and Development Corporation, aims to provide financial assistance for post-Matric/Post-Secondary technical and professional courses, including Ph.D. & M.Phil, in recognized institutions.This scholarship aims to support students with disabilities in pursuing their education and covers various courses and living arrangements.",
                "https://services.india.gov.in/service/detail/apply-online-for-scholarships-for-differently-abled",
                "any",          // Annual Family Income
                "any",                  // Caste
                "any",                   // Gender
                true                    // Disability
            )







        )

        // Check and insert scholarships if the database is empty
        if (dbHelper.getTopScholarships(3).isEmpty()) {
            scholarshipList.forEach { scholarship ->
                dbHelper.insertScholarship(scholarship)
            }
        }

        // Fetch the top 3 scholarships from the database
        val scholarshipListing = dbHelper.getTopScholarships(7)

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
