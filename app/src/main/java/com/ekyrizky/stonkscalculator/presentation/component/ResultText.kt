package com.ekyrizky.stonkscalculator.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ResultText(
    @StringRes labelResId: Int,
    result: String,
    modifier: Modifier = Modifier
) {
    val color = if (result.contains('-')) Color.Red else MaterialTheme.colors.primary
    Row {
        Text(
            text = stringResource(id = labelResId),
            modifier = modifier,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = result,
            modifier = modifier,
            color = color,
            style = MaterialTheme.typography.body1
        )
    }
}