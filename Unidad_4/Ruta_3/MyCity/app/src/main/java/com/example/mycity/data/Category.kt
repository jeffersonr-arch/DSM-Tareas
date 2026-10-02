package com.example.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mycity.R

enum class Category(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
) {
    Restaurants(
        titleRes = R.string.category_restaurants,
        descriptionRes = R.string.category_restaurants_desc,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    Parks(
        titleRes = R.string.category_parks,
        descriptionRes = R.string.category_parks_desc,
        imageRes = R.drawable.ic_launcher_foreground
    ),
    History(
        titleRes = R.string.category_history,
        descriptionRes = R.string.category_history_desc,
        imageRes = R.drawable.ic_launcher_foreground
    )
}