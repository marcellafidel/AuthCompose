package com.example.authcompose

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.authcompose.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener { if (validate()) doRegister() }
        binding.tvLogin.setOnClickListener { finish() }
    }

    private fun validate(): Boolean {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val pass = binding.etPassword.text.toString()
        val confirm = binding.etConfirm.text.toString()
        var ok = true

        binding.tilName.error = if (name.isEmpty()) "Nama wajib diisi".also { ok = false } else null
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
        binding.tilConfirm.error = if (confirm != pass) "Password tidak sama".also { ok = false } else null
        return ok
    }

    private fun doRegister() {
        // TODO: ganti dengan panggilan API / Firebase Auth
        Toast.makeText(this, "Registrasi berhasil, silakan login", Toast.LENGTH_SHORT).show()
        finish()
    }
}