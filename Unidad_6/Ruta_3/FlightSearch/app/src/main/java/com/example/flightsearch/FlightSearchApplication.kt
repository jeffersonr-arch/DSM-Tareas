package com.example.flightsearch

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightsearch.data.FlightDatabase
import com.example.flightsearch.data.UserPreferencesRepository

private const val SEARCH_PREFERENCES_NAME = "search_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SEARCH_PREFERENCES_NAME
)

class FlightSearchApplication : Application() {
    val database: FlightDatabase by lazy { FlightDatabase.getDatabase(this) }
    val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(dataStore)
    }
}