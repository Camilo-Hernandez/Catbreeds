package com.camihruiz24.catbreeds.data

import com.camihruiz24.catbreeds.core.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

fun interface CatBreedsClient {

    @Headers("x-api-key: ${Constants.API_KEY}")
    @GET(Constants.BREEDS_PATH)
    suspend fun getCatBreeds() : List<CatBreed>

}
