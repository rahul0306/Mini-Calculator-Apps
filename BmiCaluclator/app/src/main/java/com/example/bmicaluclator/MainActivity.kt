package com.example.bmicaluclator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bmicaluclator.screens.MainScreen
import com.example.bmicaluclator.ui.theme.BmiCaluclatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BmiCaluclatorTheme {
                MainScreen()
            }
        }
    }
}
