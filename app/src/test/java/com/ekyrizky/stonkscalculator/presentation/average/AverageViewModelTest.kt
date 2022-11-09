package com.ekyrizky.stonkscalculator.presentation.average

import com.ekyrizky.stonkscalculator.R
import com.ekyrizky.stonkscalculator.data.wrapper.InputWrapper
import org.junit.Assert
import org.junit.Test

class AverageViewModelTest {

    private val viewModel = AverageViewModel()

    @Test
    fun `test number of shares success`() {
        viewModel.onNumberOfSharesEntered("1000")
        Assert.assertEquals(InputWrapper("1000", null), viewModel.numberOfShares.value)
    }

    @Test
    fun `test number of shares empty`() {
        viewModel.onNumberOfSharesEntered("")
        Assert.assertEquals(InputWrapper("", R.string.error_empty), viewModel.numberOfShares.value)
    }

    @Test
    fun `test buy price success`() {
        viewModel.onBuyPriceEntered("1000")
        Assert.assertEquals(InputWrapper("1000", null), viewModel.buyPrice.value)
    }

    @Test
    fun `test buy price empty`() {
        viewModel.onBuyPriceEntered("")
        Assert.assertEquals(InputWrapper("", R.string.error_empty), viewModel.buyPrice.value)
    }

    @Test
    fun `test new number of shares success`() {
        viewModel.onNewNumberOfSharesEntered("2")
        Assert.assertEquals(InputWrapper("2", null), viewModel.newNumberOfShares.value)
    }

    @Test
    fun `test new number of shares empty`() {
        viewModel.onNewNumberOfSharesEntered("")
        Assert.assertEquals(
            InputWrapper("", R.string.error_empty),
            viewModel.newNumberOfShares.value
        )
    }

    @Test
    fun `test new buy price success`() {
        viewModel.onNewBuyPriceEntered("1000")
        Assert.assertEquals(InputWrapper("1000", null), viewModel.newBuyPrice.value)
    }

    @Test
    fun `test new buy price empty`() {
        viewModel.onNewBuyPriceEntered("")
        Assert.assertEquals(InputWrapper("", R.string.error_empty), viewModel.newBuyPrice.value)
    }

    @Test
    fun `test calculate total shares success`() {
        viewModel.onNumberOfSharesEntered("1000")
        viewModel.onBuyPriceEntered("100")
        viewModel.onNewNumberOfSharesEntered("500")
        viewModel.onNewBuyPriceEntered("92")

        viewModel.calculateTotalShares()
        val expectedOutput = "1500"
        Assert.assertEquals(expectedOutput, viewModel.totalShares.value)
    }

    @Test
    fun `test calculate average cost success`() {
        viewModel.onNumberOfSharesEntered("1000")
        viewModel.onBuyPriceEntered("100")
        viewModel.onNewNumberOfSharesEntered("500")
        viewModel.onNewBuyPriceEntered("92")

        viewModel.calculateTotalShares()
        viewModel.calculateAverageCost()
        val expectedOutput = "97.33"
        Assert.assertEquals(expectedOutput, viewModel.averageCost.value)
    }

    @Test
    fun `test reset input success`() {
        viewModel.onNumberOfSharesEntered("1000")
        viewModel.onBuyPriceEntered("100")
        viewModel.onNewNumberOfSharesEntered("500")
        viewModel.onNewBuyPriceEntered("90")
        viewModel.resetInput()

        Assert.assertEquals(InputWrapper("", null), viewModel.numberOfShares.value)
        Assert.assertEquals(InputWrapper("", null), viewModel.buyPrice.value)
        Assert.assertEquals(InputWrapper("", null), viewModel.newNumberOfShares.value)
        Assert.assertEquals(InputWrapper("", null), viewModel.newBuyPrice.value)
        Assert.assertEquals("", viewModel.totalShares.value)
        Assert.assertEquals("", viewModel.averageCost.value)
    }
}