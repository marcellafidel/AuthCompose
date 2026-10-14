package com.example.authcompose

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.authcompose.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tilEmail.error = "Masukkan email yang valid"
                return@setOnClickListener
            }
            binding.tilEmail.error = null
            // TODO: panggil API reset password
            Toast.makeText(this, "Tautan reset dikirim ke $email", Toast.LENGTH_LONG).show()
            finish()
        }
        binding.tvBack.setOnClickListener { finish() }
    }
}