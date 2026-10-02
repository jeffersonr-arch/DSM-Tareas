package com.example.mycity.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.Category
import com.example.mycity.data.LocalPlacesDataProvider
import com.example.mycity.data.Place
import com.example.mycity.ui.theme.MyCityTheme

@Composable
fun CategoryListScreen(
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(Category.entries) { category ->
            CategoryCard(
                imageRes = category.imageRes,
                title = stringResource(category.titleRes),
                subtitle = stringResource(category.descriptionRes),
                onClick = { onCategoryClick(category) }
            )
        }
    }
}

@Composable
private fun CategoryCard(
    @DrawableRes imageRes: Int,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun PlaceListScreen(
    places: List<Place>,
    selectedPlace: Place?,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(places, key = { place -> place.id }) { place ->
            PlaceCard(
                place = place,
                isSelected = place.id == selectedPlace?.id,
                onClick = { onPlaceClick(place) }
            )
        }
    }
}

@Composable
private fun PlaceCard(
    place: Place,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(place.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(96.dp)
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = stringResource(place.nameRes),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(place.districtRes),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun PlaceDetailScreen(
    place: Place,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Image(
            painter = painterResource(place.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(place.nameRes),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(place.districtRes),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(place.descriptionRes),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun PlaceListAndDetail(
    places: List<Place>,
    selectedPlace: Place,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        PlaceListScreen(
            places = places,
            selectedPlace = selectedPlace,
            onPlaceClick = onPlaceClick,
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.weight(2f)
        )
        PlaceDetailScreen(
            place = selectedPlace,
            modifier = Modifier
                .weight(3f)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListPreview() {
    MyCityTheme {
        CategoryListScreen(
            onCategoryClick = {},
            contentPadding = PaddingValues(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceListPreview() {
    MyCityTheme {
        PlaceListScreen(
            places = LocalPlacesDataProvider.getPlaces(Category.Restaurants),
            selectedPlace = null,
            onPlaceClick = {},
            contentPadding = PaddingValues(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceDetailPreview() {
    MyCityTheme {
        PlaceDetailScreen(place = LocalPlacesDataProvider.allPlaces.first())
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun PlaceListAndDetailPreview() {
    MyCityTheme {
        val places = LocalPlacesDataProvider.getPlaces(Category.Parks)
        PlaceListAndDetail(
            places = places,
            selectedPlace = places.first(),
            onPlaceClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}