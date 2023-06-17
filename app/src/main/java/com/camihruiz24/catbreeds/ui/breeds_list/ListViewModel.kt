package com.camihruiz24.catbreeds.ui.breeds_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.catbreeds.data.CatBreed
import com.camihruiz24.catbreeds.data.CatBreedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed interface HomeUiState { // TODO: mirar el <out T>
    data class Success(val catBreedsList: List<CatBreed>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

@HiltViewModel
class ListViewModel @Inject constructor(
    catBreedsRepository: CatBreedsRepository,
) : ViewModel() {
    var uiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                uiState = try {
                    val catsList = catBreedsRepository.fetchCatBreeds()
                    HomeUiState.Success(catBreedsList = catsList)
                } catch (e: Exception) {
                    HomeUiState.Error
                }
            }
        }
    }
}
