package com.example.authcompose

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.authcompose.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { if (validate()) doLogin() }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.tvForgot.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    private fun validate(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        val pass = binding.etPassword.text.toString()
        var ok = true

        binding.tilEmail.error = when {
            email.isEmpty() -> "Email wajib diisi".also { ok = false }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Format email tidak valid".also { ok = false }
            else -> null
        }
        binding.tilPassword.error = when {
            pass.isEmpty() -> "Password wajib diisi".also { ok = false }
            pass.length < 6 -> "Minimal 6 karakter".also { ok = false }
            else -> null
        }
        return ok
    }

    private fun doLogin() {
        // TODO: ganti dengan panggilan API / Firebase Auth
        val intent = Intent(this, HomeActivity::class.java)
            .putExtra("EXTRA_NAME", binding.etEmail.text.toString().substringBefore("@"))
        startActivity(intent)
        finish()
    }
}