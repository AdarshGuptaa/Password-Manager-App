package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var boxes: List<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passcode)

        boxes = listOf(
            findViewById(R.id.digit1),
            findViewById(R.id.digit2),
            findViewById(R.id.digit3),
            findViewById(R.id.digit4)
        )

        setupPasscodeInput()
    }

    private fun setupPasscodeInput() {
        boxes.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && index < boxes.lastIndex) {
                        boxes[index + 1].requestFocus()
                    } else if (s?.isEmpty() == true && index > 0) {
                        boxes[index - 1].requestFocus()
                    }

                    if (isComplete()) {
                        val passcode = getPasscode()
                        if (passcode == "1234") {
                            startActivity(Intent(this@MainActivity, VaultActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@MainActivity, "Incorrect Passcode", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun getPasscode(): String {
        return boxes.joinToString("") { it.text.toString() }
    }

    private fun isComplete(): Boolean {
        return boxes.all { it.text.length == 1 }
    }
}
