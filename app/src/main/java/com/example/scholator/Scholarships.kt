// Package declaration
package com.example.scholator

// Data class to hold the scholarship details
data class Scholarships(
    val name: String,
    val offered_by: String,
    val amount: String,
    val income: String,        // Annual family income eligibility
    val eligibility: String,
    val description: String,
    val link: String,
    val state: String,         // Applicable state
    val caste: String,         // Caste-based eligibility
    val gender: String,        // Gender-specific eligibility (e.g., Male/Female/Other)
    val disability: Boolean    // Whether it's for disabled students
)
