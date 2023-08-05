package com.camihruiz24.catbreeds.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

/**
 * Donde se requiera la interfaz, esta va a proveerse automáticamente desde la clase abstracta
 * marcada como módulo de Dagger e instalada por Hilt usando la función abstracta marcada como Binds
 * (enlace)
 */

class ItemsRepositoryImpl @Inject constructor(
    itemsDataSource: ItemsDataSource,
) : ItemsRepository {
    override val itemsData: Flow<List<ItemModel>> =
        itemsDataSource.itemsData
            .catch {
                emit(
                    listOf(
                        element = ItemModel(
                            id = "Error",
                            name = "",
                            description = "",
                            imageId = null,
                            origin = "",
                            attributes = "",
                        )
                    )
                )
            }

    override fun extractItemDetail(itemId: String): Flow<ItemModel> =
        this.itemsData
            // Se está haciendo de nuevo la consulta a la API
            .mapNotNull { itemsList ->
                itemsList.find { item ->
                    item.id == itemId
                }
            }
}

interface ItemsRepository {
    val itemsData: Flow<List<ItemModel>>
    fun extractItemDetail(itemId: String): Flow<ItemModel>
}
