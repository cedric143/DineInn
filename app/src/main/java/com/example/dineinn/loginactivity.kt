package com.example.dineinn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class loginactivity : AppCompatActivity() {
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: MaterialButton


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        auth = FirebaseAuth.getInstance()

        emailInputLayout = findViewById(R.id.txt_email)
        passwordInputLayout = findViewById(R.id.txt_password)
        loginButton = findViewById(R.id.btn_login)
        emailEditText= findViewById(R.id.input_email)
        passwordEditText = findViewById(R.id.input_password)






        loginButton.setOnClickListener {
            signInUser()
        }

        }


    private fun signInUser() {
        val email =emailEditText.text.toString()
        val password=passwordEditText.text.toString()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this,orderActivity::class.java)
                    startActivity(intent)
                    // User login successful
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    // Proceed to the next screen or perform other actions
                } else {
                    // User login failed
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
