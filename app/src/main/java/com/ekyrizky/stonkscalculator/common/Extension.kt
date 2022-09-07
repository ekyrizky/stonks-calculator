package com.ekyrizky.stonkscalculator.common

import android.content.Context
import android.widget.Toast
import java.text.DecimalFormat

fun Context.toast(messageId: Int) {
    Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show()
}

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