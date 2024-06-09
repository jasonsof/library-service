package com.example.model

import java.time.Instant.now

object LoanRepository {
    private var loans = mutableListOf<Loan>()

    fun findAll(): List<Loan> {
        return loans
    }

    fun create(book: Book, user: User): Loan {
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
}