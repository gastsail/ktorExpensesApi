package com.example.data.model

import kotlinx.serialization.Serializable

val expenses = mutableListOf(
    Expense(id = 0, 70.0, "GROCERIES", "Weekly buy"),
    Expense(id = 1, 10.2, "SNACKS", "Homies"),
    Expense(id = 2, 21000.0, "CAR", "Audi A1"),
    Expense(id = 3, 15.0, "COFFEE", "Beans and cream"),
    Expense(id = 4, 25.0, "PARTY", "Weekend party"),
    Expense(id = 5, 120.0, "HOUSE", "Expenses"),
    Expense(id = 6, 25.0, "OTHER", "CLEANING")
)

var lastExpense = expenses.size.toLong()

@Serializable
data class Expense(
    val id: Long = lastExpense++,
    val amount: Double,
    val categoryName: String,
    val description: String
)