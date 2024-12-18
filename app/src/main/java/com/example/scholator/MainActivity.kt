package com.example.scholator

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isLoginMode = true // Tracks if the user is in Login mode or Sign-Up mode
    private lateinit var dbHelper: AuthDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = AuthDatabaseHelper(this)

        val emailField = findViewById<EditText>(R.id.email)
        val passwordField = findViewById<EditText>(R.id.password)
        val signUpButton = findViewById<Button>(R.id.signup)
        val loginButton = findViewById<Button>(R.id.login)
        val toggleText = findViewById<TextView>(R.id.already_have_account)

        // Sign-Up Button Click Listener
        signUpButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            } else if (!isValidPassword(password)) {
                Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
            } else if (dbHelper.isEmailExists(email)) {
                Toast.makeText(this, "Email already exists. Please login.", Toast.LENGTH_SHORT).show()
            } else {
                val isInserted = dbHelper.insertUser(email, password)
                if (isInserted) {
                    Toast.makeText(this, "Sign-Up Successful! Please log in.", Toast.LENGTH_SHORT).show()
                    toggleToLoginMode() // Switch to Login mode
                } else {
                    Toast.makeText(this, "Sign-Up failed. Try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Login Button Click Listener
        // Login Button Click Listener
        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            } else if (dbHelper.verifyUser(email, password)) {
                // Navigate to SecondActivity on successful login
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid email or password. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }


        // Toggle Text Click Listener
        toggleText.setOnClickListener {
            if (isLoginMode) {
                toggleToSignUpMode()
            } else {
                toggleToLoginMode()
            }
        }
    }

    // Helper method to switch to Login mode
    private fun toggleToLoginMode() {
        isLoginMode = true
        findViewById<Button>(R.id.signup).visibility = Button.VISIBLE
        findViewById<Button>(R.id.login).visibility = Button.VISIBLE
        findViewById<TextView>(R.id.already_have_account).visibility = TextView.VISIBLE
    }

    // Helper method to switch to Sign-Up mode
    private fun toggleToSignUpMode() {
        isLoginMode = false
        findViewById<Button>(R.id.signup).visibility = Button.VISIBLE
        findViewById<Button>(R.id.login).visibility = Button.VISIBLE
        findViewById<TextView>(R.id.already_have_account).visibility = TextView.VISIBLE
    }

    // Validate email format
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Validate password length
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6 // Password must be at least 6 characters long
    }
}
