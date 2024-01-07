package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityLoginIntroBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LoginIntro : AppCompatActivity() {

    private lateinit var binding: ActivityLoginIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            Toast.makeText(this, "User is already Login!", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        binding.btnGetStarted.setOnClickListener {
            redirect("LOGIN")
        }
    }

    private fun redirect(name:String){
        val intent = when(name){
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("No Path Exist")
        }
        startActivity(intent)
        finish()
    }
}