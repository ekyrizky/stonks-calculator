package com.ekyrizky.stonkscalculator.presentation.component

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun CustomButton(
    @StringRes labelResId: Int,
    modifier: Modifier,
    isEnable: Boolean,
    onClick: () -> Unit,
) {
    Button(onClick = onClick, modifier = modifier, enabled = isEnable) {
        Text(text = stringResource(labelResId))
    }
}