package com.ekyrizky.stonkscalculator.presentation.utils

import com.ekyrizky.stonkscalculator.common.ScreenType

sealed class Screen(val route: String) {
    object ProfitScreen : Screen(ScreenType.profit)
    object AverageScreen : Screen(ScreenType.average)
}