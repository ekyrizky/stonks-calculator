package com.ekyrizky.stonkscalculator.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultText(
    @StringRes labelResId: Int,
    result: String,
    modifier: Modifier = Modifier
) {
    val color = if (result.contains('-')) Color.Red else Color.Green
    Row {
        Text(
            text = stringResource(id = labelResId),
            modifier = modifier,
            fontSize = 16.sp,
            letterSpacing = 1.sp,
            style = TextStyle(color = Color.Black),
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = result,
            modifier = modifier,
            fontSize = 16.sp,
            letterSpacing = 1.sp,
            style = TextStyle(color = color),
            fontWeight = FontWeight.SemiBold
        )
    }
}