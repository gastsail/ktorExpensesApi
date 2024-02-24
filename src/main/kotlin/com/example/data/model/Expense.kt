package com.example.data.model

import kotlinx.serialization.Serializable

val expenses = mutableListOf(
    Expense(id = 1, 70.0, "GROCERIES", "Weekly buy"),
    Expense(id = 2, 10.2, "SNACKS", "Homies"),
    Expense(id = 3, 21000.0, "CAR", "Audi A1"),
    Expense(id = 4, 15.0, "COFFEE", "Beans and cream"),
    Expense(id = 5, 25.0, "PARTY", "Weekend party"),
    Expense(id = 6, 120.0, "HOUSE", "Expenses"),
    Expense(id = 7, 25.0, "OTHER", "CLEANING")
)

@Serializable
data class Expense(
    val id: Long = -1,
    val amount: Double,
    val categoryName: String,
    val description: String
)