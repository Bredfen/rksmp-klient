package com.example.test.ui.sbornik

data class Product(
    val imageResId: String,  // Resource ID для drawable
    val name: String,
    val rating: Double,
    val price: Double,
    val category: String,
    val description: String
)
