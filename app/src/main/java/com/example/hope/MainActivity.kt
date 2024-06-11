package com.example.hope

import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    companion object{
        lateinit var auth: FirebaseAuth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        usernameEditText = findViewById(R.id.username_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)

        val loginButton: Button = findViewById(R.id.login_button)
        val signUpButton: Button = findViewById(R.id.signup_button)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val savedUsername = sharedPreferences.getString("Username", null)
            val savedPassword = sharedPreferences.getString("Password", null)
            if (credentialsAreValid(username, password, savedUsername, savedPassword)) {
                // Start ProfileActivity and pass the username
                val intent = Intent(this, ProfileActivity::class.java).apply {
                    putExtra("USERNAME", username)
                }
                startActivity(intent)
            }
             else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun credentialsAreValid(username: String, password: String, savedUsername: String?, savedPassword: String?): Boolean {
        return username == savedUsername && password == savedPassword
    }
}





