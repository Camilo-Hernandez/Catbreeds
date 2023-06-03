package com.camihruiz24.catbreeds.feature_list.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.catbreeds.feature_list.data.CatsApiClient
import com.camihruiz24.catbreeds.feature_list.domain.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface CatListUiState {
    data class Success(val catsList: List<Cat>) : CatListUiState
    object Error : CatListUiState
    object Loading : CatListUiState
}

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val catsApiClient: CatsApiClient,
) : ViewModel() {
    /*
        FORMA 2
        private val _uiState: MutableStateFlow<CatListUiState> = MutableStateFlow(CatListUiState())
        val uiState = _uiState.asStateFlow()
        */

    var uiState: CatListUiState by mutableStateOf(CatListUiState.Loading)
        private set

    init {
        /**
        // FORMA 2
    viewModelScope.launch{
        // Poner a cargar la vista
        _uiState.update {
            it.copy(
                isLoading = true,
            )
        }

    }
        */

        viewModelScope.launch {
            val resultadoPeticion = "Resultado de petición"
            uiState = try {

                Log.i(resultadoPeticion, "Iniciando")

                val catsList = catsApiClient.getCatBreeds()


                Log.i(resultadoPeticion, "Éxito: $catsList")

                CatListUiState.Success(catsList = catsList)

            } catch (e: Exception) {
                Log.i(resultadoPeticion, "Fallido")

                CatListUiState.Error
            }

        }
    }
}
/**
// FORMA 2
data class CatListUiState(
    val isLoading: Boolean = false,
    val catsList: List<Cat> = emptyList(),
)
*/
