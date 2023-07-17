package com.camihruiz24.catbreeds.ui.breed_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.camihruiz24.catbreeds.R
import com.camihruiz24.catbreeds.ui.util_screen.ErrorScreen
import com.camihruiz24.catbreeds.ui.util_screen.LoadingScreen

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onAddToFavorites: () -> Unit = {},
) {

    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()

    when (detailUiState) {
        is DetailUiState.Success -> CatBreedSuccessScreen(
            itemModel = (detailUiState as DetailUiState.Success).itemModel,
            onAddToFavorites = onAddToFavorites
        )
        DetailUiState.Error -> ErrorScreen(errorMessage = stringResource(R.string.error_message))
        DetailUiState.Loading -> LoadingScreen()
    }
}

