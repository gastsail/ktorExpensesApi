package com.example.routes

import com.example.data.model.MessageResponse
import com.example.data.model.Expense
import com.example.data.model.expenses
import com.example.data.model.lastExpense
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.math.max

fun Route.expensesRouting() {
    get("/expenses") {
        if(expenses.isEmpty()) {
            call.respondText { "No expenses found" }
        } else {
            call.respond(status = HttpStatusCode.OK, expenses)
        }
    }

    get("/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        if(id == null || id !in 0 until expenses.size) {
            call.respond(HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@get
        }
        val expense = expenses.find { it.id == id }
        if(expense != null) {
            call.respond(HttpStatusCode.OK, expense)
        } else {
            call.respond(HttpStatusCode.NotFound, MessageResponse("Expense not found"))
        }
    }

    post("/expenses") {
        val expense = call.receive<Expense>()
        val maxId = expenses.maxOf { it.id } + 1
        expenses.add(expense.copy(id = maxId))
        call.respond(HttpStatusCode.OK, MessageResponse("Expense added successfully"))
    }

    put("expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = call.receive<Expense>()
        if(id == null || id !in 0 until expenses.size) {
            call.respond(HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@put
        }
        val index = expenses.indexOfFirst { it.id == id }
        expenses[index] = expense.copy(id = id)
        call.respond(HttpStatusCode.OK, MessageResponse("Expense updated successfully"))
    }

    delete("expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        if(id == null || id !in 0 until expenses.size) {
            call.respond(HttpStatusCode.NotFound, MessageResponse("Expense not found"))
            return@delete
        }
        expenses.removeIf { it.id == id }
        call.respond(HttpStatusCode.OK, MessageResponse("Expense removed successfully"))
    }
}