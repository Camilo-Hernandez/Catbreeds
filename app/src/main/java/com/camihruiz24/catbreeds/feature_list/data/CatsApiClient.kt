package com.camihruiz24.catbreeds.feature_list.data

import com.camihruiz24.catbreeds.core.Constants
import com.camihruiz24.catbreeds.feature_list.domain.Cat
import retrofit2.http.GET
import retrofit2.http.Headers

fun interface CatsApiClient {

    @Headers("x-api-key: ${Constants.API_KEY}")
    @GET(Constants.BREEDS_PATH)
    suspend fun getCatBreeds() : List<Cat>

}
