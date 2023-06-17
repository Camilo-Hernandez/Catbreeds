package com.camihruiz24.catbreeds.ui.breed_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.catbreeds.data.CatBreed
import com.camihruiz24.catbreeds.data.CatBreedsRepository
import com.camihruiz24.catbreeds.ui.navigation.NavigationArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CatBreedDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val catBreedsRepository: CatBreedsRepository,
) : ViewModel() {

    private val itemId: String = checkNotNull(savedStateHandle[NavigationArg.Id.key])

    var breedDetailUiState: CatBreedDetailUiState by mutableStateOf(CatBreedDetailUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                breedDetailUiState = try {
                    val catBreed: CatBreed = catBreedsRepository.fetchCatBreedDetail(itemId)
                    CatBreedDetailUiState.Success(catBreed)
                } catch (e: IOException) {
                    CatBreedDetailUiState.Error
                }
            }
        }
    }
}

sealed interface CatBreedDetailUiState { // TODO: mirar el <out T>
    data class Success(val catBreed: CatBreed) : CatBreedDetailUiState
    object Error : CatBreedDetailUiState
    object Loading : CatBreedDetailUiState
}
