package com.example.test



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test.ui.sbornik.Product
import com.example.test.ui.sbornik.SharedViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SharedViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `addProduct increases likedProducts and basketProducts size`() {
        val viewModel = SharedViewModel()
        val product = Product("img", "Name", 4.5, 100.0, "category", "desc")

        val beforeLiked = viewModel.likedProducts.value?.size ?: 0
        val beforeBasket = viewModel.basketProducts.value?.size ?: 0

        viewModel.addProduct(product)

        val afterLiked = viewModel.likedProducts.value?.size ?: 0
        val afterBasket = viewModel.basketProducts.value?.size ?: 0

        assertEquals(beforeLiked + 1, afterLiked)
        assertEquals(beforeBasket + 1, afterBasket)
    }

    @Test
    fun `addMultipleProducts increases sizes accordingly`() {
        val viewModel = SharedViewModel()
        val products = List(5) { i ->
            Product("img$i", "Name$i", 4.0 + i, 100.0 + i, "category", "desc")
        }

        val beforeLiked = viewModel.likedProducts.value?.size ?: 0
        val beforeBasket = viewModel.basketProducts.value?.size ?: 0

        products.forEach { viewModel.addProduct(it) }

        val afterLiked = viewModel.likedProducts.value?.size ?: 0
        val afterBasket = viewModel.basketProducts.value?.size ?: 0

        assertEquals(beforeLiked + 5, afterLiked)
        assertEquals(beforeBasket + 5, afterBasket)
    }
}
