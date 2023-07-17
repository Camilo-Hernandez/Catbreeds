package com.camihruiz24.catbreeds.ui.breeds_list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.camihruiz24.catbreeds.data.ItemModel
import com.camihruiz24.catbreeds.ui.util_screen.ErrorScreen
import com.camihruiz24.catbreeds.ui.util_screen.LoadingScreen

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onNavigateToDetail: (ItemModel) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is ListUiState.Success ->
            ListSuccessScreen(
                modifier = Modifier.padding(horizontal = 8.dp),
                itemsList = (uiState as ListUiState.Success).data,
                onNavigateToDetail = onNavigateToDetail
            )
        ListUiState.Error -> ErrorScreen()
        ListUiState.Loading -> LoadingScreen()
    }
}