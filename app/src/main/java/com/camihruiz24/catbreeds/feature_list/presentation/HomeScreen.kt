package com.camihruiz24.catbreeds.feature_list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.camihruiz24.catbreeds.R

@Composable
fun HomeScreen(
    homeViewModel: CatsHomeViewModel = hiltViewModel(), // TODO: Dejarlo solamente con hiltViewModel() a ver qué tal
    onNavigateToCatDetail: () -> Unit,
) {
    when (homeViewModel.uiState) {
        is HomeUiState.Success -> CatBreedsListScreen(catsList = (homeViewModel.uiState as HomeUiState.Success).catsList, onNavigateToCatDetail = onNavigateToCatDetail)
        HomeUiState.Error -> ErrorScreen(errorMessage = stringResource(id = R.string.error_message))
        HomeUiState.Loading -> LoadingScreen()
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(16.dp)
        .fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, errorMessage : String) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}