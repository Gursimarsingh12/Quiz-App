package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart: Button = findViewById(R.id.btnStart)
        val name: EditText = findViewById(R.id.name)
        btnStart.setOnClickListener {
            if(name.text.isNotEmpty()){
                val intent = Intent(this, QuizQuestionsActivity::class.java)  // intent is used to move from one screen to other screen/ from one activity to other activity
                startActivity(intent) // here we move to other activity
                finish() // if we press back button we are out of app
            }
            else{
                Toast.makeText(this, "Please enter your name!", Toast.LENGTH_LONG).show()
            }
        }
    }
}