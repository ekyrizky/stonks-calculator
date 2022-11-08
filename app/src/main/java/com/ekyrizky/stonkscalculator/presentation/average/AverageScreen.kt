package com.ekyrizky.stonkscalculator.presentation.average

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ekyrizky.stonkscalculator.R
import com.ekyrizky.stonkscalculator.common.ScreenType
import com.ekyrizky.stonkscalculator.data.wrapper.InputWrapper
import com.ekyrizky.stonkscalculator.presentation.component.*
import com.ekyrizky.stonkscalculator.presentation.profit.ProfitViewModel

@Composable
fun AverageScreen(
    navController: NavController,
    viewModel: ProfitViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            ToolbarIcon()
            Spacer(modifier = Modifier.height(16.dp))
            ScreenDropDown(
                items = ScreenType.screens,
                modifier = Modifier.fillMaxWidth()
            ) { navController.navigate(it) }
            Spacer(modifier = Modifier.height(16.dp))
            CustomEditText(
                labelResId = R.string.number_of_shares,
                inputWrapper = InputWrapper(),
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                },
                onImeKeyAction = { },
            )
            Spacer(Modifier.height(8.dp))
            CustomEditText(
                labelResId = R.string.buy_price,
                inputWrapper = InputWrapper(),
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                },
                onImeKeyAction = { },
            )
            Spacer(Modifier.height(8.dp))
            CustomEditText(
                labelResId = R.string.new_number_of_shares,
                inputWrapper = InputWrapper(),
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                },
                onImeKeyAction = { },
            )
            Spacer(Modifier.height(8.dp))
            CustomEditText(
                labelResId = R.string.new_buy_price,
                inputWrapper = InputWrapper(),
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                },
                onImeKeyAction = { },
            )
            Spacer(Modifier.height(32.dp))
            Row {
                CustomButton(
                    labelResId = R.string.calculate,
                    modifier = Modifier.weight(1f),
                    isEnable = false,
                    onClick = { },
                )
                Spacer(modifier = Modifier.width(16.dp))
                CustomButton(
                    labelResId = R.string.reset,
                    modifier = Modifier.weight(1f),
                    isEnable = true,
                    onClick = { },
                )
            }
            Spacer(Modifier.height(16.dp))
            ResultText(labelResId = R.string.total_shares, result = "")
            Spacer(Modifier.height(12.dp))
            ResultText(labelResId = R.string.average_cost, result = "")
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    AverageScreen(rememberNavController())
}