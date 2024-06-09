package com.example

import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun fetchAllBooks() = testApplication {
        val jsonDoc = client.getAsJsonPath("/books")

        val result: List<String> = jsonDoc.read("$[*].title")
        assertEquals("The Light Fantastic", result[0])
        assertEquals("Piranesi", result[1])
        assertEquals("Saga of the swamp thing", result[2])
        assertEquals("Choose Your Own Apocalypse With Kim Jong-un & Friends", result[3])
        assertEquals("Jonathan Strange and Mr Norrel", result[4])
    }

    @Test
    fun fetchBooksByProperty() = testApplication {
        val jsonDoc = client.getAsJsonPath("/books?property=author&query=Susanna%20Clarke")

        val result: List<String> = jsonDoc.read("$[*].title")
        assertEquals("Piranesi", result[0])
        assertEquals("Jonathan Strange and Mr Norrel", result[1])
    }

    @Test
    fun fetchBooksByPropertyWithAnInvalidQueryProperty() = testApplication {
        val response = client.get("/books?property=publisher&query=Susanna%20Clarke")

        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    suspend fun HttpClient.getAsJsonPath(url: String): DocumentContext {
        val response = this.get(url) {
            accept(ContentType.Application.Json)
        }
        return JsonPath.parse(response.bodyAsText())
    }
}