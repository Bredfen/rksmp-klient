package com.example.test.ui.order

data class ItemZakaz(

    val numberZakaz: String,
    val dateZakaz: String,
    val adress: String,
    val numberPhone: String,
    val EdinicaTovara: List<EdinicaTovara>
)
