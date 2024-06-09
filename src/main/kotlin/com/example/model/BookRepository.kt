package com.example.model

object BookRepository {
    private val books = mutableListOf(
        Book(1, "The Light Fantastic", "Terry Pratchett", "055216660X"),
        Book(2, "Piranesi", "Susanna Clarke", "1526622424"),
        Book(3, "Saga of the swamp thing", "Alan Moore", "B00NBE21DG"),
        Book(4, "Choose Your Own Apocalypse With Kim Jong-un & Friends", "Rob Sears", "1786898640"),
        Book(5, "Jonathan Strange and Mr Norrel", "Susanna Clarke", "1786898643")
    )

    fun findAll(): List<Book> {
        return books
    }

    fun findBy(filterProperty: String, filterValue: String): List<Book> {
        if (filterProperty.lowercase() !in FilterableProperties.entries.map { t -> t.name.lowercase() }) throw Exception("Not a valid filter property: $filterProperty")

        val filteredBooks = books.filter { book ->
            when (filterProperty.lowercase()) {
                FilterableProperties.Title.name.lowercase() -> book.title.contains(filterValue, ignoreCase = true)
                FilterableProperties.Author.name.lowercase() -> book.author.contains(filterValue, ignoreCase = true)
                FilterableProperties.ISBN.name.lowercase() -> book.isbn.contains(filterValue, ignoreCase = true)
                else -> false
            }
        }
        return filteredBooks
    }

    fun findById(id: Int): Book {
        return books.find { it.id == id } ?: throw Exception("Book not found")
    }
}