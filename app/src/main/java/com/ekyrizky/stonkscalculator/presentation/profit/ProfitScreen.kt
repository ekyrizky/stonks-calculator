package com.ekyrizky.stonkscalculator.presentation.profit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ekyrizky.stonkscalculator.R
import com.ekyrizky.stonkscalculator.common.ScreenType
import com.ekyrizky.stonkscalculator.common.toast
import com.ekyrizky.stonkscalculator.presentation.component.*
import com.ekyrizky.stonkscalculator.presentation.utils.ScreenEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfitScreen(
    navController: NavController,
    viewModel: ProfitViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val numberOfShares by viewModel.numberOfShares.collectAsState()
    val buyPrices by viewModel.buyPrice.collectAsState()
    val sellPrices by viewModel.sellPrice.collectAsState()
    val buyCommission by viewModel.buyCommission.collectAsState()
    val sellCommission by viewModel.sellCommission.collectAsState()
    val profit by viewModel.profit.collectAsState()
    val areInputsValid by viewModel.areInputsValid.collectAsState()

    val events = remember(viewModel.events, lifecycleOwner) {
        viewModel.events.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is ScreenEvent.ShowToast -> context.toast(event.messageId)
                is ScreenEvent.UpdateKeyboard -> {
                    if (event.show) keyboardController?.show() else keyboardController?.hide()
                }
                is ScreenEvent.ClearFocus -> focusManager.clearFocus()
                is ScreenEvent.MoveFocus -> focusManager.moveFocus(event.direction)
                is ScreenEvent.ChangeScreen -> navController.navigate(event.screen)
            }
        }
    }

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
                selectedItem = ScreenType.profit,
                modifier = Modifier.fillMaxWidth(),
                onClick = viewModel::onDropDownClick
            )
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
                onImeKeyAction = viewModel::onImeActionClick,
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
                onImeKeyAction = viewModel::onImeActionClick,
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
                onImeKeyAction = viewModel::onImeActionClick,
            )
            Spacer(Modifier.height(8.dp))
            CustomEditText(
                labelResId = R.string.buy_commission,
                inputWrapper = buyCommission,
                onValueChange = viewModel::onBuyCommissionEntered,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                },
                onImeKeyAction = viewModel::onImeActionClick,
                trailingIcons = {
                    Text(
                        text = "%",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
            Spacer(Modifier.height(8.dp))
            CustomEditText(
                labelResId = R.string.sell_commission,
                inputWrapper = sellCommission,
                onValueChange = viewModel::onSellCommissionEntered,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                },
                onImeKeyAction = viewModel::onCalculateClick,
                trailingIcons = {
                    Text(
                        text = "%",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
            Spacer(Modifier.height(32.dp))
            Row {
                CustomButton(
                    labelResId = R.string.calculate,
                    modifier = Modifier.weight(1f),
                    isEnable = areInputsValid,
                    onClick = viewModel::onCalculateClick,
                )
                Spacer(modifier = Modifier.width(16.dp))
                CustomButton(
                    labelResId = R.string.reset,
                    modifier = Modifier.weight(1f),
                    isEnable = true,
                    onClick = viewModel::onResetClick,
                )
            }
            Spacer(Modifier.height(16.dp))
            ResultText(labelResId = R.string.profit, result = profit)
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    ProfitScreen(rememberNavController())
}