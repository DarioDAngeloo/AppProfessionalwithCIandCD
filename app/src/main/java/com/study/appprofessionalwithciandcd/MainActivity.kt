package com.study.appprofessionalwithciandcd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.study.appprofessionalwithciandcd.ui.theme.MaterialCalculatorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialCalculatorTheme {
                
            }
            }
        }
    }


