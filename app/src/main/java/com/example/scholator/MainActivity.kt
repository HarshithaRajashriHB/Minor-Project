package com.example.scholator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isLoginMode = true // Tracks if the user is in Login mode or Sign-Up mode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Find views by ID
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
            } else if (isLoginMode) {
                Toast.makeText(this, "Sign-Up Successful! Please log in.", Toast.LENGTH_SHORT).show()
                toggleToLoginMode() // Switch to Login mode
            }
        }

        // Login Button Click Listener
        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }else if (isLoginMode) {
                // Navigate to SecondActivity on successful login
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
             else {
                Toast.makeText(this, "Sign Up first to log in.", Toast.LENGTH_SHORT).show()
            }
        }

        // Toggle Text Click Listener
        toggleText.setOnClickListener {
            if (isLoginMode) {
                Toast.makeText(this, "Switching to Sign-Up mode.", Toast.LENGTH_SHORT).show()
                toggleToSignUpMode()
            } else {
                Toast.makeText(this, "Switching to Login mode.", Toast.LENGTH_SHORT).show()
                toggleToLoginMode()
            }
        }
    }

    // Helper method to switch to Login mode
    private fun toggleToLoginMode() {
        isLoginMode = true
    }

    // Helper method to switch to Sign-Up mode
    private fun toggleToSignUpMode() {
        isLoginMode = false
    }
}
