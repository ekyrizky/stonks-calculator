package com.ekyrizky.stonkscalculator.common

import com.ekyrizky.stonkscalculator.R
import org.junit.Assert.assertEquals
import org.junit.Test

class InputValidatorTest {

    @Test
    fun `test getInputErrorOrNull with input value`() {
        val input = "5000"
        val errorId = InputValidator.getInputErrorOrNull(input)
        assertEquals(null, errorId)
    }

    @Test
    fun `test getInputErrorOrNull with empty input`() {
        val input = ""
        val errorId = InputValidator.getInputErrorOrNull(input)
        assertEquals(R.string.error_empty, errorId)
    }

    @Test
    fun `test getFilteredInput right input format`() {
        val input = "12345"
        val inputDecimal1 = "12.345"
        val inputDecimala2 = ".345"

        val filteredInput = InputValidator.getFilteredInput(input)
        val filteredInputDecimal1 = InputValidator.getFilteredInput(inputDecimal1)
        val filteredInputDecimal2 = InputValidator.getFilteredInput(inputDecimala2)

        val expectedInput = "12345"
        val expectInputDecimal1 = "12.345"
        val expectInputDecimal2 = ".345"
        assertEquals(expectedInput, filteredInput)
        assertEquals(expectInputDecimal1, filteredInputDecimal1)
        assertEquals(expectInputDecimal2, filteredInputDecimal2)
    }

    @Test
    fun `test getFilteredInput wrong input format`() {
        val input1 = "12.34."
        val input2 = "1234..."
        val input3 = ".234."
        val input4 = "5,"

        val filteredInput1 = InputValidator.getFilteredInput(input1)
        val filteredInput2 = InputValidator.getFilteredInput(input2)
        val filteredInput3 = InputValidator.getFilteredInput(input3)
        val filteredInput4 = InputValidator.getFilteredInput(input4)

        val expectedInput1 = "12.34"
        val expectedInput2 = "1234."
        val expectedInput3 = ".234"
        val expectedInput4 = "5"

        assertEquals(expectedInput1, filteredInput1)
        assertEquals(expectedInput2, filteredInput2)
        assertEquals(expectedInput3, filteredInput3)
        assertEquals(expectedInput4, filteredInput4)
    }
}