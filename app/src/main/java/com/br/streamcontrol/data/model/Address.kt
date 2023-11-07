package com.br.streamcontrol.data.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("house_number")
    val houseNumber: String,
    @SerializedName("road")
    val road: String,
    @SerializedName("residential")
    val residential: String,
    @SerializedName("suburb")
    val suburb: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("municipality")
    val municipality: String,
    @SerializedName("state_district")
    val stateDistrict: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_code")
    val countryCode: String
)