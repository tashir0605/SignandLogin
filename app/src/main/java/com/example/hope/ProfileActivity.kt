package com.example.hope

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileImageView: ImageView
    private lateinit var welcomeTextView: TextView
    private lateinit var academicsButton: Button
    private lateinit var aboutCollegeButton: Button
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileImageView = findViewById(R.id.profileImageView)
        welcomeTextView = findViewById(R.id.welcomeTextView)
        academicsButton = findViewById(R.id.academicsButton)
        aboutCollegeButton = findViewById(R.id.aboutCollegeButton)

        // Get the username passed from the login activity
        val username = intent.getStringExtra("USERNAME")
        welcomeTextView.text = "Hi,\n$username"

        // Set up the profile image view to allow changing the profile photo
        profileImageView.setOnClickListener {
            dispatchTakePictureIntent()
        }

        // Set up buttons for navigating to other activities
        academicsButton.setOnClickListener {
            // Handle Academics button click
        }

        aboutCollegeButton.setOnClickListener {
            // Handle About College button click
        }
    }
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            profileImageView.setImageBitmap(imageBitmap)
        }
    }
}
