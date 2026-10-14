package com.example.authcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.authcompose.ui.AppTheme
import com.example.authcompose.ui.HomeScreen

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("EXTRA_NAME").orEmpty().ifBlank { "Teman" }
        setContent {
            AppTheme {
                HomeScreen(userName = name)
            }
        }
    }
}