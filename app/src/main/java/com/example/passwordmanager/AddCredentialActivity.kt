package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddCredentialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val saveButton = findViewById<Button>(R.id.saveButton)
        val websiteEditText = findViewById<EditText>(R.id.editWebsite)
        val usernameEditText = findViewById<EditText>(R.id.editUsername)
        val passwordEditText = findViewById<EditText>(R.id.editPassword)

        saveButton.setOnClickListener {
            val website = websiteEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Here you can handle saving the credentials to your database or list
            if (website.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()) {
                // Add the credential to your list or database
                Toast.makeText(this, "Credential Saved", Toast.LENGTH_SHORT).show()

                // Optionally navigate back to the vault or another activity
                finish()
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

