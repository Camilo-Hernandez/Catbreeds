package com.camihruiz24.catbreeds.feature_list.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.catbreeds.feature_list.data.Cat
import com.camihruiz24.catbreeds.feature_list.data.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface CatListUiState { // TODO: mirar el <out T>
    data class Success(val catsList: List<Cat>) : CatListUiState
    object Error : CatListUiState
    object Loading : CatListUiState
}

@HiltViewModel
class CatListViewModel @Inject constructor(
    catsRepository: CatsRepository
) : ViewModel() {
    var uiState: CatListUiState by mutableStateOf(CatListUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            val requestResult = "Resultado de petición"
            uiState = try {

                Log.i(requestResult, "Iniciando")

                val catsList = catsRepository.getCatBreeds()

                Log.i(requestResult, "Éxito: $catsList")

                CatListUiState.Success(catsList = catsList)

            } catch (e: Exception) {
                Log.i(requestResult, "Fallido")

                CatListUiState.Error
            }

        }
    }
}
