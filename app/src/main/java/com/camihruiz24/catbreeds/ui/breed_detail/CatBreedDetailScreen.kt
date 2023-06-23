package com.camihruiz24.catbreeds.ui.breed_detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.camihruiz24.catbreeds.R
import com.camihruiz24.catbreeds.ui.util_screen.ErrorScreen
import com.camihruiz24.catbreeds.ui.util_screen.LoadingScreen

@Composable
fun CatBreedDetailScreen(
    catBreedDetailViewModel: CatBreedDetailViewModel = hiltViewModel(),
    onAddToFavorites: () -> Unit = {},
) {
    when (catBreedDetailViewModel.breedDetailUiState) {
        CatBreedDetailUiState.Error -> ErrorScreen(errorMessage = stringResource(R.string.error_message))
        CatBreedDetailUiState.Loading -> LoadingScreen()
        is CatBreedDetailUiState.Success -> CatBreedSuccessScreen(
            catBreed = (catBreedDetailViewModel.breedDetailUiState as CatBreedDetailUiState.Success).catBreed,
            onAddToFavorites = onAddToFavorites
        )
    }
}

