package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Loan (
    val id: Int,
    val userID: Int,
    val bookID: Int,
    val date: Long, // epoch time - ideally I would de-serialize this into a date object
    val returned: Boolean
)