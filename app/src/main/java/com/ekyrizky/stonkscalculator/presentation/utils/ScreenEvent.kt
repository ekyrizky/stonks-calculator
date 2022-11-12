package com.ekyrizky.stonkscalculator.presentation.utils

import androidx.compose.ui.focus.FocusDirection
import com.ekyrizky.stonkscalculator.common.ScreenType

sealed class ScreenEvent {
    class ShowToast(val messageId: Int) : ScreenEvent()
    class UpdateKeyboard(val show: Boolean) : ScreenEvent()
    object ClearFocus : ScreenEvent()
    class MoveFocus(val direction: FocusDirection = FocusDirection.Down) : ScreenEvent()
    class ChangeScreen(val screen: String = ScreenType.profit) : ScreenEvent()
}