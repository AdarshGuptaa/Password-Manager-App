package com.example.passwordmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VaultActivity : AppCompatActivity() {

    private lateinit var credentialList: RecyclerView
    private lateinit var adapter: CredentialAdapter
    private val credentials = mutableListOf<Credential>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vault)

        val websiteEditText = findViewById<EditText>(R.id.editWebsite)
        val usernameEditText = findViewById<EditText>(R.id.editUsername)
        val passwordEditText = findViewById<EditText>(R.id.editPassword)
        val saveButton = findViewById<Button>(R.id.saveButton)

        credentialList = findViewById(R.id.credentialList)
        credentialList.layoutManager = LinearLayoutManager(this)
        adapter = CredentialAdapter(this, credentials)

        credentialList.adapter = adapter

        saveButton.setOnClickListener {
            val website = websiteEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (website.isNotBlank() && username.isNotBlank() && password.isNotBlank()) {
                val credential = Credential(website, username, password)
                credentials.add(credential)
                adapter.notifyItemInserted(credentials.size - 1)

                websiteEditText.text.clear()
                usernameEditText.text.clear()
                passwordEditText.text.clear()
            }
        }
    }
}

