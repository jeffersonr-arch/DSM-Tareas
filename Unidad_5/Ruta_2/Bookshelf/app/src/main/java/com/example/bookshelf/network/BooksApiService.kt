package com.example.bookshelf.network

import com.example.bookshelf.BuildConfig
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BooksSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 10,
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): BooksSearchResponse


    @GET("volumes/{id}")
    suspend fun getBook(
        @Path("id") id: String,
        @Query("key") apiKey: String = BuildConfig.BOOKS_API_KEY
    ): BookItem
}