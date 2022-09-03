package com.ekyrizky.stonkscalculator.presentation.profit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ekyrizky.stonkscalculator.R
import com.ekyrizky.stonkscalculator.presentation.component.CustomButton
import com.ekyrizky.stonkscalculator.presentation.component.CustomEditText
import com.ekyrizky.stonkscalculator.presentation.component.ResultText
import com.ekyrizky.stonkscalculator.presentation.component.ToolbarIcon

@Composable
fun ProfitCalculatorScreen(viewModel: ProfitViewModel = hiltViewModel()) {

    val numberOfShares by viewModel.numberOfShares.collectAsState()
    val buyPrices by viewModel.buyPrice.collectAsState()
    val sellPrices by viewModel.sellPrice.collectAsState()
    val buyCommission by viewModel.buyCommission.collectAsState()
    val sellCommission by viewModel.sellCommission.collectAsState()
    val profit by viewModel.profit.collectAsState()
    val areInputsValid by viewModel.areInputsValid.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        ToolbarIcon()
        Spacer(modifier = Modifier.height(16.dp))
        CustomEditText(
            labelResId = R.string.number_of_shares,
            inputWrapper = numberOfShares,
            onValueChange = viewModel::onNumberOfSharesEntered,
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
            inputWrapper = buyPrices,
            onValueChange = viewModel::onBuyPriceEntered,
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
            labelResId = R.string.sell_price,
            inputWrapper = sellPrices,
            onValueChange = viewModel::onSellPriceEntered,
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
        Row {
            CustomEditText(
                labelResId = R.string.buy_commission,
                inputWrapper = buyCommission,
                onValueChange = viewModel::onBuyCommissionEntered,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                },
                onImeKeyAction = { },
                trailingIcons = {
                    Text(
                        text = "%",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            CustomEditText(
                labelResId = R.string.sell_commission,
                inputWrapper = sellCommission,
                onValueChange = viewModel::onSellCommissionEntered,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                },
                onImeKeyAction = { },
                trailingIcons = {
                    Text(
                        text = "%",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        }
        Spacer(Modifier.height(32.dp))
        Row {
            CustomButton(
                labelResId = R.string.calculate,
                modifier = Modifier.weight(1f),
                isEnable = areInputsValid,
                onClick = viewModel::calculateProfit,
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
        ResultText(labelResId = R.string.profit, result = profit)
    }
}