package com.camihruiz24.catbreeds.ui.breeds_list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.camihruiz24.catbreeds.R
import com.camihruiz24.catbreeds.data.CatBreed
import com.camihruiz24.catbreeds.ui.util_screen.ErrorScreen
import com.camihruiz24.catbreeds.ui.util_screen.LoadingScreen

@Composable
fun ListScreen(
    listViewModel: ListViewModel = hiltViewModel(),
    onNavigateToDetail: (CatBreed) -> Unit,
) {
    when (listViewModel.uiState) {
        is HomeUiState.Success ->
            ListSuccessScreen(
                modifier = Modifier.padding(horizontal = 8.dp),
                catBreedsList = (listViewModel.uiState as HomeUiState.Success).catBreedsList,
                onNavigateToDetail = onNavigateToDetail
            )

        HomeUiState.Error -> ErrorScreen(errorMessage = stringResource(id = R.string.error_message))
        HomeUiState.Loading -> LoadingScreen()
    }
}