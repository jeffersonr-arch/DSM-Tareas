package com.example.bookshelf.model

data class BooksSearchResponse(
    val items: List<BookItem>? = null
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo? = null
)

data class VolumeInfo(
    val title: String? = null,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
)

data class Book(
    val id: String,
    val title: String,
    val thumbnailUrl: String
)