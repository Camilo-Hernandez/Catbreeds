package com.camihruiz24.catbreeds.ui.breed_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.catbreeds.data.ItemsRepository
import com.camihruiz24.catbreeds.data.ItemModel
import com.camihruiz24.catbreeds.ui.navigation.NavigationArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    itemsRepository: ItemsRepository,
) : ViewModel() {

    private val itemId: String = checkNotNull(savedStateHandle[NavigationArg.Id.key])

    var detailUiState: StateFlow<DetailUiState> = itemsRepository.extractItemDetail(itemId)
        .map {
            DetailUiState.Success(it)
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = DetailUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )
}

sealed interface DetailUiState { // TODO: mirar el <out T>
    data class Success(val itemModel: ItemModel) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}


