package com.ekyrizky.stonkscalculator.common

import com.ekyrizky.stonkscalculator.R

object InputValidator {

    fun getInputErrorOrNull(input: String): Int? {
        return when {
            input.isEmpty() -> R.string.error_empty
            else -> null
        }
    }

    /**
     * input only accept number 0 to 9 and contain one dot (decimal point) from keyboard
     * input that not meet requirement will be filtered in textfield onValueChange
     */
    fun getFilteredInput(input: String): String {
        return input.filterIndexed { index, value ->
            value in '0'..'9' || (value == '.' && input.indexOf('.') == index)
        }
    }
}