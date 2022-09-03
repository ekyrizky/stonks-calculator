package com.ekyrizky.stonkscalculator.presentation.profit

import com.ekyrizky.stonkscalculator.R
import com.ekyrizky.stonkscalculator.data.wrapper.InputWrapper
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfitViewModelTest {

    private val viewModel = ProfitViewModel()

    @Test
    fun `test number of shares success`() {
        viewModel.onNumberOfSharesEntered("1000")
        assertEquals(InputWrapper("1000", null), viewModel.numberOfShares.value)
    }

    @Test
    fun `test number of shares empty`() {
        viewModel.onNumberOfSharesEntered("")
        assertEquals(InputWrapper("", R.string.error_empty), viewModel.numberOfShares.value)
    }

    @Test
    fun `test buy price success`() {
        viewModel.onBuyPriceEntered("1000")
        assertEquals(InputWrapper("1000", null), viewModel.buyPrice.value)
    }

    @Test
    fun `test buy price empty`() {
        viewModel.onBuyPriceEntered("")
        assertEquals(InputWrapper("", R.string.error_empty), viewModel.buyPrice.value)
    }

    @Test
    fun `test sell price success`() {
        viewModel.onSellPriceEntered("1000")
        assertEquals(InputWrapper("1000", null), viewModel.sellPrice.value)
    }

    @Test
    fun `test sell price empty`() {
        viewModel.onSellPriceEntered("")
        assertEquals(InputWrapper("", R.string.error_empty), viewModel.sellPrice.value)
    }

    @Test
    fun `test buy commission success`() {
        viewModel.onBuyCommissionEntered("2")
        assertEquals(InputWrapper("2", null), viewModel.buyCommission.value)
    }

    @Test
    fun `test buy commission empty`() {
        viewModel.onBuyCommissionEntered("")
        assertEquals(InputWrapper("", R.string.error_empty), viewModel.buyCommission.value)
    }

    @Test
    fun `test sell commission success`() {
        viewModel.onSellCommissionEntered("2.5")
        assertEquals(InputWrapper("2.5", null), viewModel.sellCommission.value)
    }

    @Test
    fun `test sell commission empty`() {
        viewModel.onSellCommissionEntered("")
        assertEquals(InputWrapper("", R.string.error_empty), viewModel.sellCommission.value)
    }

    @Test
    fun `test calculate profit success`() {
        viewModel.onNumberOfSharesEntered("1000")
        viewModel.onBuyPriceEntered("50")
        viewModel.onSellPriceEntered("60")
        viewModel.onBuyCommissionEntered("2")
        viewModel.onSellCommissionEntered("3")

        viewModel.calculateProfit()
        val expectedOutput = "7,200 (14.12 %)"
        assertEquals(expectedOutput, viewModel.profit.value)
    }

    @Test
    fun `test reset input success`() {
        viewModel.onNumberOfSharesEntered("1000")
        viewModel.onBuyPriceEntered("50")
        viewModel.onSellPriceEntered("60")
        viewModel.onBuyCommissionEntered("2")
        viewModel.onSellCommissionEntered("3")
        viewModel.resetInput()

        assertEquals(InputWrapper("", null), viewModel.numberOfShares.value)
        assertEquals(InputWrapper("", null), viewModel.buyPrice.value)
        assertEquals(InputWrapper("", null), viewModel.sellPrice.value)
        assertEquals(InputWrapper("", null), viewModel.buyCommission.value)
        assertEquals(InputWrapper("", null), viewModel.sellCommission.value)
        assertEquals("", viewModel.profit.value)
    }
}