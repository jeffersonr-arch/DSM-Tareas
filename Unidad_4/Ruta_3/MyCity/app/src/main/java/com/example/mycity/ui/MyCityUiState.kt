package com.example.mycity.ui

import com.example.mycity.data.Category
import com.example.mycity.data.LocalPlacesDataProvider
import com.example.mycity.data.Place

data class MyCityUiState(
    val currentCategory: Category = Category.Restaurants,
    val places: List<Place> = LocalPlacesDataProvider.getPlaces(currentCategory),
    val currentPlace: Place = places.first()
)