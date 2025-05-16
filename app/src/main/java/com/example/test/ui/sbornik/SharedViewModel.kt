package com.example.test.ui.sbornik

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _likedProducts = MutableLiveData<MutableList<Product>>(mutableListOf())
    private val _basketProducts = MutableLiveData<MutableList<Product>>(mutableListOf())
    val likedProducts: LiveData<MutableList<Product>> get() = _likedProducts
    val basketProducts: LiveData<MutableList<Product>> get() = _basketProducts

    fun addProduct(product: Product) {
        val currentList = _likedProducts.value ?: mutableListOf()
        currentList.add(product)
        _likedProducts.value = currentList

        val currentListbsket = _basketProducts.value ?: mutableListOf()
        currentListbsket.add(product)
        _basketProducts.value = currentListbsket
    }
}
