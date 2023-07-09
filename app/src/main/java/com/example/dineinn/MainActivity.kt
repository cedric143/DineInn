package com.example.dineinn


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var buttonordernow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonordernow=findViewById(R.id.btn_ordernow)

        buttonordernow.setOnClickListener {
            val intent=Intent(this,registrationActivity::class.java)
            startActivity(intent)




        }

    }



}

