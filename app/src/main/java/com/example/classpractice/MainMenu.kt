package com.example.classpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val textView = findViewById<TextView>(R.id.textView)
        val username =intent.getStringExtra("username")
        textView.text = username
    }
}