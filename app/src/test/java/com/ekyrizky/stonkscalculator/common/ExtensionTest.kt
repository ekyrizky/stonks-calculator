package com.ekyrizky.stonkscalculator.common

import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionTest {

    @Test
    fun `test format decimal success`() {
        val input1 = "1000"
        val input2 = "100000"
        val input3 = "10000.52"

        assertEquals("1,000", input1.formatDecimal())
        assertEquals("100,000", input2.formatDecimal())
        assertEquals("10,000.52", input3.formatDecimal())
    }

    @Test
    fun `test format decimal empty`() {
        val input = ""
        assertEquals("", input.formatDecimal())
    }

    @Test
    fun `test format decimal failed parse`() {
        val input = "abc"
        assertEquals("", input.formatDecimal())
    }
}