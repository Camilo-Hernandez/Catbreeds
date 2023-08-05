package com.camihruiz24.catbreeds.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItemsDataSourceImpl @Inject constructor(
    private val apiServiceClient: ApiServiceClient,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : ItemsDataSource {

    override val itemsData: Flow<List<ItemModel>> =
        // TODO: Realizar una BD con Room donde cachear la respuesta y que la app funcione offline first
        flow {
            emit(apiServiceClient.getItemsData())
        }
            .flowOn(ioDispatcher)
            .also {
                Log.i(
                    "Respuesta de la lista", it.toString()
                )
            }

}

interface ItemsDataSource {
    val itemsData: Flow<List<ItemModel>>
}
