package com.camihruiz24.catbreeds.data

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("reference_image_id")
    val imageId : String?,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("temperament")
    val attributes: String,
)

