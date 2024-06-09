package com.example.model

object BookRepository {
    private val books = mutableListOf(
        Book(1, "The Light Fantastic", "Terry Pratchett", "055216660X"),
        Book(2, "Piranesi", "Susanna Clarke", "1526622424"),
        Book(3, "Saga of the swamp thing", "Alan Moore", "B00NBE21DG"),
        Book(4, "Choose Your Own Apocalypse With Kim Jong-un & Friends", "Rob Sears", "1786898640")
    )

    fun findAll(): List<Book> {
        return books
    }
}