package com.ekyrizky.stonkscalculator.presentation.average

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekyrizky.stonkscalculator.R
import com.ekyrizky.stonkscalculator.common.InputValidator
import com.ekyrizky.stonkscalculator.common.formatDecimal
import com.ekyrizky.stonkscalculator.data.wrapper.InputWrapper
import com.ekyrizky.stonkscalculator.presentation.utils.ScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AverageViewModel @Inject constructor() : ViewModel() {

    private val _numberOfShares = MutableStateFlow(InputWrapper())
    val numberOfShares = _numberOfShares.asStateFlow()

    private val _buyPrice = MutableStateFlow(InputWrapper())
    val buyPrice = _buyPrice.asStateFlow()

    private val _newNumberOfShares = MutableStateFlow(InputWrapper())
    val newNumberOfShares = _newNumberOfShares.asStateFlow()

    private val _newBuyPrice = MutableStateFlow(InputWrapper())
    val newBuyPrice = _newBuyPrice.asStateFlow()

    private val _totalShares = MutableStateFlow("")
    val totalShares = _totalShares.asStateFlow()

    private val _averageCost = MutableStateFlow("")
    val averageCost = _averageCost.asStateFlow()

    private val _events = Channel<ScreenEvent>()
    val events = _events.receiveAsFlow()

    val areInputsValid = combine(
        numberOfShares,
        buyPrice,
        newNumberOfShares,
        newBuyPrice
    ) { numberOfShares, buyPrice, newNumberOfShares, newBuyPrice ->
        numberOfShares.value.isNotEmpty() && numberOfShares.errorId == null
                && buyPrice.value.isNotEmpty() && buyPrice.errorId == null
                && newNumberOfShares.value.isNotEmpty() && newNumberOfShares.errorId == null
                && newBuyPrice.value.isNotEmpty() && newBuyPrice.errorId == null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    fun onNumberOfSharesEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _numberOfShares.value = InputWrapper(input, errorId)
    }

    fun onBuyPriceEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _buyPrice.value = InputWrapper(input, errorId)
    }

    fun onNewNumberOfSharesEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _newNumberOfShares.value = InputWrapper(input, errorId)
    }

    fun onNewBuyPriceEntered(input: String) {
        val errorId = InputValidator.getInputErrorOrNull(input)
        _newBuyPrice.value = InputWrapper(input, errorId)
    }

    fun onCalculateClick() {
        viewModelScope.launch(Dispatchers.Default) {
            if (areInputsValid.value) {
                calculateTotalShares()
                calculateAverageCost()
                clearFocusAndHideKeyboard()
            } else {
                _events.send(ScreenEvent.ShowToast(R.string.error_empty))
            }
        }
    }

    fun onResetClick() {
        viewModelScope.launch(Dispatchers.Default) {
            resetInput()
            clearFocusAndHideKeyboard()
        }
    }

    fun onImeActionClick() {
        viewModelScope.launch(Dispatchers.Default) {
            _events.send(ScreenEvent.MoveFocus())
        }
    }

    fun calculateTotalShares() {
        _totalShares.value =
            "${numberOfShares.value.value.toDouble() + newNumberOfShares.value.value.toDouble()}"
    }

    fun calculateAverageCost() {
        val sharesOwned = buyPrice.value.value.toDouble() * numberOfShares.value.value.toDouble()
        val newShares =
            newBuyPrice.value.value.toDouble() * newNumberOfShares.value.value.toDouble()
        val averageCost = (sharesOwned + newShares) / totalShares.value.toDouble()
        _averageCost.value = averageCost.toString().formatDecimal()
    }

    fun resetInput() {
        _numberOfShares.value = InputWrapper("", null)
        _buyPrice.value = InputWrapper("", null)
        _newNumberOfShares.value = InputWrapper("", null)
        _newBuyPrice.value = InputWrapper("", null)
        _totalShares.value = ""
        _averageCost.value = ""
    }

    private suspend fun clearFocusAndHideKeyboard() {
        _events.send(ScreenEvent.ClearFocus)
        _events.send(ScreenEvent.UpdateKeyboard(false))
    }
}