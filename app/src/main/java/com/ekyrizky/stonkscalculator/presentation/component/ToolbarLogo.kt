package com.ekyrizky.stonkscalculator.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ekyrizky.stonkscalculator.R

@Composable
fun ToolbarIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_stonks_long),
        contentDescription = stringResource(id = R.string.app_logo),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        alignment = Alignment.TopStart
    )
}