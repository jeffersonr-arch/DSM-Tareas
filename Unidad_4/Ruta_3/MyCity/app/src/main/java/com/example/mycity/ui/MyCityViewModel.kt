package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.Category
import com.example.mycity.data.LocalPlacesDataProvider
import com.example.mycity.data.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun updateCurrentCategory(category: Category) {
        val places = LocalPlacesDataProvider.getPlaces(category)
        _uiState.update { currentState ->
            currentState.copy(
                currentCategory = category,
                places = places,
                currentPlace = places.first()
            )
        }
    }

    fun updateCurrentPlace(place: Place) {
        _uiState.update { currentState ->
            currentState.copy(currentPlace = place)
        }
    }
}