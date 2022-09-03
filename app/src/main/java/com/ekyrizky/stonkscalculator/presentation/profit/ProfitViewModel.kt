package com.ekyrizky.stonkscalculator.presentation.profit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekyrizky.stonkscalculator.common.InputValidator
import com.ekyrizky.stonkscalculator.common.formatDecimal
import com.ekyrizky.stonkscalculator.data.wrapper.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfitViewModel @Inject constructor() : ViewModel() {

    private val _numberOfShares = MutableStateFlow(InputWrapper())
    val numberOfShares = _numberOfShares.asStateFlow()

    private val _buyPrice = MutableStateFlow(InputWrapper())
    val buyPrice = _buyPrice.asStateFlow()

    private val _sellPrice = MutableStateFlow(InputWrapper())
    val sellPrice = _sellPrice.asStateFlow()

    private val _buyCommission = MutableStateFlow(InputWrapper())
    val buyCommission = _buyCommission.asStateFlow()

    private val _sellCommission = MutableStateFlow(InputWrapper())
    val sellCommission = _sellCommission.asStateFlow()

    private val _profit = MutableStateFlow("")
    val profit = _profit.asStateFlow()

    val areInputsValid = combine(
        numberOfShares,
        buyPrice,
        sellPrice,
        buyCommission,
        sellCommission
    ) { numberOfShares, buyPrice, sellPrice, buyCommission, sellCommission ->
        numberOfShares.value.isNotEmpty() && numberOfShares.errorId == null
                && buyPrice.value.isNotEmpty() && buyPrice.errorId == null
                && sellPrice.value.isNotEmpty() && sellPrice.errorId == null
                && buyCommission.value.isNotEmpty() && buyCommission.errorId == null
                && sellCommission.value.isNotEmpty() && sellCommission.errorId == null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    fun onNumberOfSharesEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _numberOfShares.value = InputWrapper(input, errorId)
    }

    fun onBuyPriceEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _buyPrice.value = InputWrapper(input, errorId)
    }

    fun onSellPriceEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _sellPrice.value = InputWrapper(input, errorId)
    }

    fun onBuyCommissionEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _buyCommission.value = InputWrapper(input, errorId)
    }

    fun onSellCommissionEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _sellCommission.value = InputWrapper(input, errorId)
    }

    fun calculateProfit() {
        val buyPrice = buyPrice.value.value.toDouble() * numberOfShares.value.value.toDouble()
        val sellPrice = sellPrice.value.value.toDouble() * numberOfShares.value.value.toDouble()
        val netBuy = buyPrice + (buyPrice * buyCommission.value.value.toDouble() / 100)
        val netSell = sellPrice - (sellPrice * sellCommission.value.value.toDouble() / 100)
        val profit = netSell - netBuy
        val roi = (profit / netBuy * 100).toString()

        _profit.value = "${profit.toString().formatDecimal()} (${roi.formatDecimal()} %)"
    }
}