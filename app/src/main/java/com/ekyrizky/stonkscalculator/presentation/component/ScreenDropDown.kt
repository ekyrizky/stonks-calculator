package com.ekyrizky.stonkscalculator.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomSpinner(
    items: List<String>,
    modifier: Modifier = Modifier,
    onClick: (value: String) -> Unit
) {
    var dropDownText by remember { mutableStateOf(items[0]) }
    var expanded by remember { mutableStateOf(false) }
    Box(modifier, contentAlignment = Alignment.Center) {
        Row(
            modifier.clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dropDownText,
                modifier = Modifier.padding(end = 8.dp),
                style = MaterialTheme.typography.h5
            )
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "drop down menu")
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        onClick(item)
                        dropDownText = item
                    }) {
                        Text(
                            text = item,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}