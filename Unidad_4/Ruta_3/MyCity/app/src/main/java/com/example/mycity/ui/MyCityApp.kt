package com.example.mycity.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.ui.utils.MyCityContentType

enum class MyCityScreen {
    Categories,
    Places,
    Detail
}

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    viewModel: MyCityViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Categories.name
    )

    val contentType = if (windowSize == WindowWidthSizeClass.Expanded) {
        MyCityContentType.ListAndDetail
    } else {
        MyCityContentType.ListOnly
    }

    val title = when (currentScreen) {
        MyCityScreen.Categories -> stringResource(R.string.app_name)
        MyCityScreen.Places -> stringResource(uiState.currentCategory.titleRes)
        MyCityScreen.Detail -> stringResource(uiState.currentPlace.nameRes)
    }

    Scaffold(
        topBar = {
            MyCityAppBar(
                title = title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Categories.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.Categories.name) {
                CategoryListScreen(
                    onCategoryClick = { category ->
                        viewModel.updateCurrentCategory(category)
                        navController.navigate(MyCityScreen.Places.name)
                    },
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = MyCityScreen.Places.name) {
                if (contentType == MyCityContentType.ListAndDetail) {
                    PlaceListAndDetail(
                        places = uiState.places,
                        selectedPlace = uiState.currentPlace,
                        onPlaceClick = { place -> viewModel.updateCurrentPlace(place) },
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    PlaceListScreen(
                        places = uiState.places,
                        selectedPlace = null,
                        onPlaceClick = { place ->
                            viewModel.updateCurrentPlace(place)
                            navController.navigate(MyCityScreen.Detail.name)
                        },
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            composable(route = MyCityScreen.Detail.name) {
                PlaceDetailScreen(
                    place = uiState.currentPlace,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}