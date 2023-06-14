package com.camihruiz24.catbreeds.feature_list.data

import javax.inject.Inject

/**
 * Donde se requiera la interfaz, esta va a proveerse automáticamente desde la clase abstracta
 * marcada como módulo de Dagger e instalada por Hilt usando la función abstracta marcada como Binds
 * (enlace)
 */

class CatBreedsRepositoryImpl @Inject constructor(
    private val catBreedsService: CatBreedsService,
    ) : CatBreedsRepository {
    override suspend fun getCatBreeds(): List<Cat> = catBreedsService.getCatBreeds()

}

fun interface CatBreedsRepository {
    suspend fun getCatBreeds() : List<Cat>
}