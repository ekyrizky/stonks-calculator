package com.ekyrizky.stonkscalculator.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ekyrizky.stonkscalculator.common.InputValidator
import com.ekyrizky.stonkscalculator.data.wrapper.InputWrapper

@Composable
fun CustomEditText(
    @StringRes labelResId: Int,
    inputWrapper: InputWrapper,
    onValueChange: (value: String) -> Unit,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions = remember { KeyboardOptions.Default },
    onImeKeyAction: () -> Unit,
    trailingIcons: @Composable (() -> Unit)? = null,
) {
    val fieldValue = remember { mutableStateOf(inputWrapper.value) }
    OutlinedTextField(
        value = if (inputWrapper.value.isEmpty()) "" else fieldValue.value,
        onValueChange = {
            val value = InputValidator.getFilteredInput(it)
            fieldValue.value = value
            onValueChange(value)
        },
        modifier = modifier,
        label = {
            Text(
                text = stringResource(labelResId),
                style = MaterialTheme.typography.body1
            )
        },
        trailingIcon = trailingIcons,
        isError = inputWrapper.errorId != null,
        keyboardOptions = keyboardOptions,
        keyboardActions = remember { KeyboardActions(onAny = { onImeKeyAction() }) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.primary,
            backgroundColor = MaterialTheme.colors.background
        )
    )

    if (inputWrapper.errorId != null) {
        Text(
            text = stringResource(inputWrapper.errorId),
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
        )
    }
}