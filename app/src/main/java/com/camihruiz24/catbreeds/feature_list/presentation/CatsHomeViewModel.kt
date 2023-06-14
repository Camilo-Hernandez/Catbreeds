package com.camihruiz24.catbreeds.feature_list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.catbreeds.feature_list.data.Cat
import com.camihruiz24.catbreeds.feature_list.data.CatBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface HomeUiState { // TODO: mirar el <out T>
    data class Success(val catsList: List<Cat>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

@HiltViewModel
class CatsHomeViewModel @Inject constructor(
    catBreedsRepository: CatBreedsRepository
) : ViewModel() {
    var uiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            uiState = try {
                val catsList = catBreedsRepository.getCatBreeds()
                HomeUiState.Success(catsList = catsList)
            } catch (e: Exception) {
                HomeUiState.Error
            }
        }
    }
}
