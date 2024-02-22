package com.example.routes

import com.example.data.model.ErrorResponse
import com.example.data.model.Expense
import com.example.data.model.expenses
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.expensesRouting() {
    get("/expenses") {
        if (expenses.isEmpty()) {
            call.respondText("No expenses found", status = HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.OK, expenses)
        }
    }

    get("/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        if (id == null || id !in 0 until expenses.size) {
            call.respond(HttpStatusCode.NotFound, ErrorResponse("Expense not found"))
            return@get
        }
        call.respond(HttpStatusCode.OK, expenses[id.toInt()])
    }

    post("/expenses") {
        val expense = call.receive<Expense>().copy(id = expenses.size.toLong() + 1)
        expenses.add(expense)
        call.respond(HttpStatusCode.Created, "Expense added successfully")
    }

    put("/expenses/{id}") {
        val id = call.parameters["id"]?.toLongOrNull()
        val expense = call.receive<Expense>()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid expense ID"))
            return@put
        }
        val index = expenses.indexOfFirst { it.id == id }
        if (index == -1) {
            call.respond(HttpStatusCode.NotFound, ErrorResponse("Expense not found"))
            return@put
        }
        expenses[index] = expense.copy(id = id)
        call.respond(HttpStatusCode.OK, "Expense updated successfully")
    }
}