package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightDao
import com.example.flightsearch.data.UserPreferencesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class FlightSearchUiState(
    val showFavorites: Boolean = false,
    val selectedAirport: Airport? = null,
    val suggestions: List<Airport> = emptyList(),
    val destinations: List<Airport> = emptyList(),
    val favorites: List<Favorite> = emptyList()
)

@OptIn(ExperimentalCoroutinesApi::class)
class FlightSearchViewModel(
    private val flightDao: FlightDao,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    val uiState: StateFlow<FlightSearchUiState> = _searchText
        .map { it.trim() }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                flightDao.getAllFavorites().map { favorites ->
                    FlightSearchUiState(showFavorites = true, favorites = favorites)
                }
            } else {
                flightDao.getAirportByCode(query).flatMapLatest { airport ->
                    if (airport != null) {
                        combine(
                            flightDao.getDestinations(airport.iataCode),
                            flightDao.getAllFavorites()
                        ) { destinations, favorites ->
                            FlightSearchUiState(
                                selectedAirport = airport,
                                destinations = destinations,
                                favorites = favorites
                            )
                        }
                    } else {
                        flightDao.searchAirports(query).map { suggestions ->
                            FlightSearchUiState(suggestions = suggestions)
                        }
                    }
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FlightSearchUiState()
        )

    init {

        viewModelScope.launch {
            _searchText.value = userPreferencesRepository.searchText.first()
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        viewModelScope.launch {
            userPreferencesRepository.saveSearchText(text)
        }
    }

    fun onAirportSelected(airport: Airport) {
        onSearchTextChange(airport.iataCode)
    }

    fun onFavoriteClick(departureCode: String, destinationCode: String) {
        viewModelScope.launch {
            val isFavorite = uiState.value.favorites.any {
                it.departureCode == departureCode && it.destinationCode == destinationCode
            }
            if (isFavorite) {
                flightDao.deleteFavorite(departureCode, destinationCode)
            } else {
                flightDao.insertFavorite(
                    Favorite(departureCode = departureCode, destinationCode = destinationCode)
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                FlightSearchViewModel(
                    application.database.flightDao(),
                    application.userPreferencesRepository
                )
            }
        }
    }
}