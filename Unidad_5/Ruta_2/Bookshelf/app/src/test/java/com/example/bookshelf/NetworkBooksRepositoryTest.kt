package com.example.bookshelf

import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.fake.FakeBooksApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {

    @Test
    fun networkBooksRepository_getBooks_skipsBooksWithoutThumbnail() = runTest {
        val repository = NetworkBooksRepository(booksApiService = FakeBooksApiService())

        val books = repository.getBooks("jazz")

        assertEquals(2, books.size)
    }

    @Test
    fun networkBooksRepository_getBooks_replacesHttpWithHttps() = runTest {
        val repository = NetworkBooksRepository(booksApiService = FakeBooksApiService())

        val books = repository.getBooks("jazz")

        assertEquals("https://ejemplo.com/portada1.jpg", books[0].thumbnailUrl)
        assertEquals("Historia del Jazz", books[0].title)
    }
}