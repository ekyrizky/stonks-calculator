package com.ekyrizky.stonkscalculator.common

import java.text.DecimalFormat

fun String.formatDecimal(): String {
    return if (this.isNotEmpty()) {
        try {
            val dec = DecimalFormat("#,###.##")
            dec.format(this.toDouble())
        } catch (e: Exception) {
            ""
        }
    } else {
        ""
    }
}