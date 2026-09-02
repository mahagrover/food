package com.example.foodapp

data class FoodItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val emoji: String,
    var quantity: Int = 0
)
