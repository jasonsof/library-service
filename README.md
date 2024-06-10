# Library Service Task

My Kotlin solution to the book-lending library requirements using ktor framework.

## Usage

In IntelliJ IDEA run file `src/main/kotlin/com.example/Application.kt`

Access the endpoints from URL: `http://0.0.0.0:8080/books` in browser or using Postman.  Postman testing snippets below.

API documentation:
https://documenter.getpostman.com/view/32117258/2sA3XLEPYZ

## Tests

In IntelliJ IDEA run file `src/test/kotlin/com.example/ApplicationTest.kt`

## Assumptions
- there would be a logged in user
- cant borrow a book already on loan
