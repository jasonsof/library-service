package com.example.model

import java.time.Instant.now

object LoanRepository {
    private var loans = mutableListOf<Loan>()

    fun findAll(): List<Loan> {
        return loans
    }

    fun create(book: Book, user: User): Loan {
        if (bookOnLoan(book)) throw Exception("Book already on loan")

        val loan = Loan(
            loans.size + 1,
            user.id,
            book.id,
            now().toEpochMilli(),
            false
        )
        loans.add(loan)

        return loan
    }

    // this would be better suited to a member function on the Book class
    // in reality persistence layer would look this up with SQL query instead of iterating all loans!
    private fun bookOnLoan(book: Book): Boolean {
        return loans.any { loan -> loan.bookID == book.id && !loan.returned }
    }
}