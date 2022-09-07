package com.ekyrizky.stonkscalculator.common

import androidx.compose.ui.focus.FocusDirection

sealed class ScreenEvent {
    class ShowToast(val messageId: Int) : ScreenEvent()
    class UpdateKeyboard(val show: Boolean) : ScreenEvent()
    object ClearFocus : ScreenEvent()
    class MoveFocus(val direction: FocusDirection = FocusDirection.Down) : ScreenEvent()
}