package com.example.bookshelf.fake

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BooksSearchResponse
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

object FakeDataSource {

    val searchResponse = BooksSearchResponse(
        items = listOf(
            BookItem(id = "libro1"),
            BookItem(id = "libro2"),
            BookItem(id = "libro3")
        )
    )

    val bookDetails = mapOf(
        "libro1" to BookItem(
            id = "libro1",
            volumeInfo = VolumeInfo(
                title = "Historia del Jazz",
                imageLinks = ImageLinks(thumbnail = "http://ejemplo.com/portada1.jpg")
            )
        ),
        "libro2" to BookItem(
            id = "libro2",
            volumeInfo = VolumeInfo(
                title = "Kotlin para Android",
                imageLinks = ImageLinks(thumbnail = "http://ejemplo.com/portada2.jpg")
            )
        ),
        "libro3" to BookItem(
            id = "libro3",
            volumeInfo = VolumeInfo(title = "Libro sin portada", imageLinks = null)
        )
    )
}