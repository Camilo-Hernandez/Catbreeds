package com.camihruiz24.catbreeds.ui.breeds_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.catbreeds.data.ItemsRepository
import com.camihruiz24.catbreeds.data.ItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface ListUiState<out T> { // TODO: mirar el <out T>
    data class Success<T>(val data: T) : ListUiState<T>
    object Error : ListUiState<Nothing>
    object Loading : ListUiState<Nothing>
}

@HiltViewModel
class ListViewModel @Inject constructor(
    itemsRepository: ItemsRepository,
) : ViewModel() {

    val uiState: StateFlow<ListUiState<List<ItemModel>>> = itemsRepository.itemsData
        .map {
            ListUiState.Success(it)
        }
        .catch {
            ListUiState.Error
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = ListUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )
}
