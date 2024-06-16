package com.example.hope


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView

class NameActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var profileImageView: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_name)

        usernameEditText = findViewById(R.id.username_edit_text)
        profileImageView = findViewById(R.id.logo)

        val saveButton: Button = findViewById(R.id.save)
        val nextButton: Button = findViewById(R.id.next)

        saveButton.setOnClickListener {
            val name = usernameEditText.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
            } else {
                // Save the username in SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("Username", name)
                editor.apply()
                Toast.makeText(this, "Username saved", Toast.LENGTH_SHORT).show()
                Log.d("NameActivity", "Username saved: $name")
            }
        }

        nextButton.setOnClickListener {
            val name = usernameEditText.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
            } else {
                // Save the username in SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("Username", name)
                editor.apply()

                // Start ProfileActivity with the username
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("USERNAME", name)
                startActivity(intent)
            }
        }
    }
}


