package com.example.flightsearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.R
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.ui.theme.FlightSearchTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchApp(
    modifier: Modifier = Modifier,
    viewModel: FlightSearchViewModel = viewModel(factory = FlightSearchViewModel.Factory)
) {
    val searchText by viewModel.searchText.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            SearchField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            val selectedAirport = uiState.selectedAirport
            when {
                selectedAirport != null -> FlightList(
                    departure = selectedAirport,
                    destinations = uiState.destinations,
                    favorites = uiState.favorites,
                    onFavoriteClick = { destination ->
                        viewModel.onFavoriteClick(selectedAirport.iataCode, destination.iataCode)
                    }
                )
                uiState.showFavorites -> FavoriteList(
                    favorites = uiState.favorites,
                    onFavoriteClick = { favorite ->
                        viewModel.onFavoriteClick(favorite.departureCode, favorite.destinationCode)
                    }
                )
                else -> SuggestionList(
                    suggestions = uiState.suggestions,
                    onSuggestionClick = viewModel::onAirportSelected
                )
            }
        }
    }
}

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.search_hint)) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.clear_search)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(28.dp)
    )
}

@Composable
fun SuggestionList(
    suggestions: List<Airport>,
    onSuggestionClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(suggestions, key = { it.id }) { airport ->
            AirportLine(
                code = airport.iataCode,
                name = airport.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSuggestionClick(airport) }
                    .padding(vertical = 12.dp)
            )
            HorizontalDivider()
        }
    }
}
@Composable
fun FlightList(
    departure: Airport,
    destinations: List<Airport>,
    favorites: List<Favorite>,
    onFavoriteClick: (Airport) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.flights_from, departure.iataCode),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(destinations, key = { it.id }) { destination ->
                val isFavorite = favorites.any {
                    it.departureCode == departure.iataCode &&
                            it.destinationCode == destination.iataCode
                }
                RouteCard(
                    departureCode = departure.iataCode,
                    departureName = departure.name,
                    destinationCode = destination.iataCode,
                    destinationName = destination.name,
                    isFavorite = isFavorite,
                    onFavoriteClick = { onFavoriteClick(destination) }
                )
            }
        }
    }
}

@Composable
fun FavoriteList(
    favorites: List<Favorite>,
    onFavoriteClick: (Favorite) -> Unit,
    modifier: Modifier = Modifier
) {
    if (favorites.isEmpty()) {
        Text(
            text = stringResource(R.string.no_favorites),
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(16.dp)
        )
    } else {
        Column(modifier = modifier) {
            Text(
                text = stringResource(R.string.favorite_routes),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favorites, key = { it.id }) { favorite ->
                    RouteCard(
                        departureCode = favorite.departureCode,
                        departureName = null,
                        destinationCode = favorite.destinationCode,
                        destinationName = null,
                        isFavorite = true,
                        onFavoriteClick = { onFavoriteClick(favorite) }
                    )
                }
            }
        }
    }
}


@Composable
fun AirportLine(
    code: String,
    name: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = code,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(56.dp)
        )
        if (name != null) {
            Text(text = name, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun RouteCard(
    departureCode: String,
    departureName: String?,
    destinationCode: String,
    destinationName: String?,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.depart),
                    style = MaterialTheme.typography.labelSmall
                )
                AirportLine(code = departureCode, name = departureName)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.arrive),
                    style = MaterialTheme.typography.labelSmall
                )
                AirportLine(code = destinationCode, name = destinationName)
            }
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(
                        if (isFavorite) R.string.remove_favorite else R.string.add_favorite
                    ),
                    tint = if (isFavorite) Color(0xFFFFB300) else MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RouteCardPreview() {
    FlightSearchTheme {
        RouteCard(
            departureCode = "MUC",
            departureName = "Munich International Airport",
            destinationCode = "LIS",
            destinationName = "Humberto Delgado Airport",
            isFavorite = true,
            onFavoriteClick = {}
        )
    }
}