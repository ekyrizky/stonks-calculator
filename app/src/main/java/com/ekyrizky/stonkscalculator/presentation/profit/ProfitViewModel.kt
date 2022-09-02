package com.ekyrizky.stonkscalculator.presentation.profit

import androidx.lifecycle.ViewModel
import com.ekyrizky.stonkscalculator.common.InputValidator
import com.ekyrizky.stonkscalculator.data.wrapper.InputWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
}