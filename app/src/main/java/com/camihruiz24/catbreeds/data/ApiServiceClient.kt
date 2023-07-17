package com.camihruiz24.catbreeds.data

import com.camihruiz24.catbreeds.core.Constants
import retrofit2.http.GET
import retrofit2.http.Headers

fun interface ApiServiceClient {
    @Headers("x-api-key: ${Constants.API_KEY}")
    @GET(Constants.BREEDS_PATH)
    suspend fun getItemsData(): List<ItemModel>
}
