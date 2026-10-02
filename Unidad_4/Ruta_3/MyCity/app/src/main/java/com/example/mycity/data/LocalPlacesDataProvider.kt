package com.example.mycity.data

import com.example.mycity.R

object LocalPlacesDataProvider {

    val allPlaces: List<Place> = listOf(
        Place(
            id = 1,
            nameRes = R.string.place_central_name,
            districtRes = R.string.place_central_district,
            descriptionRes = R.string.place_central_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.Restaurants
        ),
        Place(
            id = 2,
            nameRes = R.string.place_maido_name,
            districtRes = R.string.place_maido_district,
            descriptionRes = R.string.place_maido_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.Restaurants
        ),
        Place(
            id = 3,
            nameRes = R.string.place_la_mar_name,
            districtRes = R.string.place_la_mar_district,
            descriptionRes = R.string.place_la_mar_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.Restaurants
        ),
        Place(
            id = 4,
            nameRes = R.string.place_isolina_name,
            districtRes = R.string.place_isolina_district,
            descriptionRes = R.string.place_isolina_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.Restaurants
        ),
        Place(
            id = 5,
            nameRes = R.string.place_kennedy_name,
            districtRes = R.string.place_kennedy_district,
            descriptionRes = R.string.place_kennedy_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.Parks
        ),
        Place(
            id = 6,
            nameRes = R.string.place_amor_name,
            districtRes = R.string.place_amor_district,
            descriptionRes = R.string.place_amor_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.Parks
        ),
        Place(
            id = 7,
            nameRes = R.string.place_circuito_name,
            districtRes = R.string.place_circuito_district,
            descriptionRes = R.string.place_circuito_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.Parks
        ),
        Place(
            id = 8,
            nameRes = R.string.place_olivar_name,
            districtRes = R.string.place_olivar_district,
            descriptionRes = R.string.place_olivar_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.Parks
        ),
        Place(
            id = 9,
            nameRes = R.string.place_plaza_name,
            districtRes = R.string.place_plaza_district,
            descriptionRes = R.string.place_plaza_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.History
        ),
        Place(
            id = 10,
            nameRes = R.string.place_san_francisco_name,
            districtRes = R.string.place_san_francisco_district,
            descriptionRes = R.string.place_san_francisco_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.History
        ),
        Place(
            id = 11,
            nameRes = R.string.place_larco_name,
            districtRes = R.string.place_larco_district,
            descriptionRes = R.string.place_larco_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.History
        ),
        Place(
            id = 12,
            nameRes = R.string.place_pucllana_name,
            districtRes = R.string.place_pucllana_district,
            descriptionRes = R.string.place_pucllana_desc,
            imageRes = R.drawable.ic_launcher_foreground,
            category = Category.History
        )
    )

    fun getPlaces(category: Category): List<Place> =
        allPlaces.filter { it.category == category }
}