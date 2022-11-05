package com.ekyrizky.stonkscalculator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ekyrizky.stonkscalculator.presentation.average.AverageScreen
import com.ekyrizky.stonkscalculator.presentation.profit.ProfitScreen
import com.ekyrizky.stonkscalculator.presentation.ui.theme.StonksCalculatorTheme
import com.ekyrizky.stonkscalculator.presentation.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StonksCalculatorTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.ProfitScreen.route,
                ) {
                    composable(route = Screen.ProfitScreen.route) {
                        ProfitScreen()
                    }
                    composable(route = Screen.AverageScreen.route) {
                        AverageScreen()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StonksCalculatorTheme {
        ProfitScreen()
    }
}