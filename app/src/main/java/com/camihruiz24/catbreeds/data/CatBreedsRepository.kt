package com.camihruiz24.catbreeds.data

import android.util.Log
import javax.inject.Inject

/**
 * Donde se requiera la interfaz, esta va a proveerse automáticamente desde la clase abstracta
 * marcada como módulo de Dagger e instalada por Hilt usando la función abstracta marcada como Binds
 * (enlace)
 */

class CatBreedsRepositoryImpl @Inject constructor(
    private val catBreedsDataSource: CatBreedsDataSource,
) : CatBreedsRepository {
    override suspend fun fetchCatBreeds(): List<CatBreed> =
        catBreedsDataSource.fetchCatBreeds()


    override suspend fun fetchCatBreedDetail(breedId: String): CatBreed {
        val catBreeds = this.fetchCatBreeds() // Se está haciendo de nuevo la consulta a la API
        val catBreedDetail = catBreeds.find {
            it.id == breedId
        }
        Log.i("Respuesta del detalle", "$catBreedDetail")
        return catBreedDetail!!
    }

}

interface CatBreedsRepository {
    suspend fun fetchCatBreeds(): List<CatBreed>
    suspend fun fetchCatBreedDetail(breedId: String): CatBreed
}
