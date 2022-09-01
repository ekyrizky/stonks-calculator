package com.ekyrizky.stonkscalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ekyrizky.stonkscalculator.presentation.ProfitCalculatorScreen
import com.ekyrizky.stonkscalculator.presentation.ui.theme.StonksCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StonksCalculatorTheme {
                ProfitCalculatorScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StonksCalculatorTheme {
        ProfitCalculatorScreen()
    }
}