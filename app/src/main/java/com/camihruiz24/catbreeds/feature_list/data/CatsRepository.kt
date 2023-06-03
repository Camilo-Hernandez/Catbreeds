package com.camihruiz24.catbreeds.feature_list.data

import javax.inject.Inject

class CatsRepository @Inject constructor(
    private val catsApiService: CatsApiService,
    ) {
    suspend fun getCatBreeds() = catsApiService.getCatBreeds()
}