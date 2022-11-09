package com.ekyrizky.stonkscalculator.presentation.average

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun AverageScreen(
    navController: NavController,
    viewModel: AverageViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val numberOfShares by viewModel.numberOfShares.collectAsState()
    val buyPrices by viewModel.buyPrice.collectAsState()
    val newNumberOfShares by viewModel.newNumberOfShares.collectAsState()
    val newBuyPrices by viewModel.newBuyPrice.collectAsState()
    val totalShares by viewModel.totalShares.collectAsState()
    val averageCost by viewModel.averageCost.collectAsState()
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
                modifier = Modifier.fillMaxWidth()
            ) { navController.navigate(it) }
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
                labelResId = R.string.new_number_of_shares,
                inputWrapper = newNumberOfShares,
                onValueChange = viewModel::onNewNumberOfSharesEntered,
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
                labelResId = R.string.new_buy_price,
                inputWrapper = newBuyPrices,
                onValueChange = viewModel::onNewBuyPriceEntered,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = remember {
                    KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                },
                onImeKeyAction = viewModel::onCalculateClick,
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
            ResultText(labelResId = R.string.total_shares, result = totalShares)
            Spacer(Modifier.height(12.dp))
            ResultText(labelResId = R.string.average_cost, result = averageCost)
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    AverageScreen(rememberNavController())
}