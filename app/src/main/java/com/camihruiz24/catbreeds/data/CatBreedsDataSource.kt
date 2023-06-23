package com.camihruiz24.catbreeds.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatBreedsDataSourceImpl @Inject constructor(
    private val catBreedsClient: CatBreedsClient,
) : CatBreedsDataSource {
    override suspend fun fetchCatBreeds(): List<CatBreed> =
        // TODO: Realizar una BD con Room donde cachear la respuesta y que la app funcione offline first
        withContext(Dispatchers.IO){
            val catBreeds = catBreedsClient.getCatBreeds()
            Log.i("Respuesta de la lista", "$catBreeds")
            catBreeds
        }
}

fun interface CatBreedsDataSource {

    suspend fun fetchCatBreeds(): List<CatBreed>

}
