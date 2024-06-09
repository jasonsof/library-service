package com.example.plugins

import com.example.model.BookRepository
import com.example.model.FilterableProperties
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
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