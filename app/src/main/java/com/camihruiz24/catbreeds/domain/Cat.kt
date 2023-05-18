package com.camihruiz24.catbreeds.domain


import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("adaptability")
    val adaptability: Int = 0,
    @SerializedName("affection_level")
    val affectionLevel: Int = 0,
    @SerializedName("alt_names")
    val altNames: String? = null,
    @SerializedName("bidability")
    val bidability: Int? = null,
    @SerializedName("cat_friendly")
    val catFriendly: Int? = null,
    @SerializedName("cfa_url")
    val cfaUrl: String? = null,
    @SerializedName("child_friendly")
    val childFriendly: Int = 0,
    @SerializedName("country_code")
    val countryCode: String = "",
    @SerializedName("country_codes")
    val countryCodes: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("dog_friendly")
    val dogFriendly: Int = 0,
    @SerializedName("energy_level")
    val energyLevel: Int = 0,
    @SerializedName("experimental")
    val experimental: Int = 0,
    @SerializedName("grooming")
    val grooming: Int = 0,
    @SerializedName("hairless")
    val hairless: Int = 0,
    @SerializedName("health_issues")
    val healthIssues: Int = 0,
    @SerializedName("hypoallergenic")
    val hypoallergenic: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("indoor")
    val indoor: Int = 0,
    @SerializedName("intelligence")
    val intelligence: Int = 0,
    @SerializedName("lap")
    val lap: Int? = null,
    @SerializedName("life_span")
    val lifeSpan: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("natural")
    val natural: Int = 0,
    @SerializedName("origin")
    val origin: String = "",
    @SerializedName("rare")
    val rare: Int = 0,
    @SerializedName("reference_image_id")
    val referenceImageId: String? = null,
    @SerializedName("rex")
    val rex: Int = 0,
    @SerializedName("shedding_level")
    val sheddingLevel: Int = 0,
    @SerializedName("short_legs")
    val shortLegs: Int = 0,
    @SerializedName("social_needs")
    val socialNeeds: Int = 0,
    @SerializedName("stranger_friendly")
    val strangerFriendly: Int = 0,
    @SerializedName("suppressed_tail")
    val suppressedTail: Int = 0,
    @SerializedName("temperament")
    val temperament: String = "",
    @SerializedName("vcahospitals_url")
    val vcahospitalsUrl: String? = null,
    @SerializedName("vetstreet_url")
    val vetstreetUrl: String? = null,
    @SerializedName("vocalisation")
    val vocalisation: Int = 0,
    @SerializedName("weight")
    val weight: Weight = Weight(),
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String? = null,
) {
    data class Weight(
        @SerializedName("imperial")
        val imperial: String = "",
        @SerializedName("metric")
        val metric: String = "",
    )
}