package com.example.dineinn

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.dineinn.MainActivity
import com.example.dineinn.R

class splashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay the execution of the next activity
        Handler().postDelayed({
            // Start the main activity after the splash time out
            val intent = Intent(this@splashActivity, MainActivity::class.java)
            startActivity(intent)

            // Close the splash activity
            finish()
        }, splashTimeOut)
    }
}
