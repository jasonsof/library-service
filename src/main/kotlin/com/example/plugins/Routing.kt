package com.example.plugins

import UserRepository
import com.example.model.BookRepository
import com.example.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/books") {
            get {
                val queryProperties = getQueryProperties(call)

                if(queryProperties == null) {
                    call.respond(BookRepository.findAll())
                    return@get
                }

                try {
                    call.respond(
                        BookRepository.findBy(queryProperties.first, queryProperties.second)
                    )
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request :(")
                }
            }

            post("/{bookId}/loans") {
                val bookId: Int = call.parameters["bookId"]?.toInt() ?: return@post call.respond(HttpStatusCode.BadRequest)

                try {
                    val book: Book = BookRepository.findById(bookId)
                    val loan = LoanRepository.create(book, currentUser())
                    call.respond(HttpStatusCode.Created, loan)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.NotFound, e.message ?: "Book could not be loaned :(")
                }
            }
        }

        route("/loans") {
            get {
                val allLoans: List<Loan> = LoanRepository.findAll()
                call.respond(allLoans)
            }

            get("/count") {
                val count: Int = LoanRepository.findAll().size
                call.respond(count)
            }
        }
    }
}

private fun getQueryProperties(call: ApplicationCall): Pair<String, String>? {
    val queryParameters = call.request.queryParameters

    return if (queryParameters.isEmpty()) {
        null
    } else {
        Pair(queryParameters["property"] ?: "", queryParameters["query"]?.decodeURLQueryComponent() ?: "")
    }
}

// this would return the logged in user in reality
private fun currentUser(): User {
    return UserRepository.users.first()
}