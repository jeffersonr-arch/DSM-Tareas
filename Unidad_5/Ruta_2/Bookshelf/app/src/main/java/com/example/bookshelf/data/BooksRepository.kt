package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> {

        val searchResult = booksApiService.searchBooks(query)
        val books = mutableListOf<Book>()

        for (item in searchResult.items.orEmpty()) {
            val detail = booksApiService.getBook(item.id)
            val thumbnail = detail.volumeInfo?.imageLinks?.thumbnail ?: continue
            books.add(
                Book(
                    id = item.id,
                    title = detail.volumeInfo?.title ?: "Sin título",

                    thumbnailUrl = thumbnail.replace("http://", "https://")
                )
            )
        }
        return books
    }
}