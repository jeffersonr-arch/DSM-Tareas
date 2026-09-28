package com.example.bookshelf.fake

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BooksSearchResponse
import com.example.bookshelf.network.BooksApiService

class FakeBooksApiService : BooksApiService {

    override suspend fun searchBooks(
        query: String,
        maxResults: Int,
        apiKey: String
    ): BooksSearchResponse = FakeDataSource.searchResponse

    override suspend fun getBook(id: String, apiKey: String): BookItem =
        FakeDataSource.bookDetails.getValue(id)
}