package com.example.plugins

import com.example.model.BookRepository
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/books") {
            get {
                call.respond(BookRepository.findAll())
            }
        }
    }
}
