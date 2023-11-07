package com.br.streamcontrol.data.remote.dto.response

import com.br.streamcontrol.data.model.Address
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("place_id")
    val placeId: Long,
    @SerializedName("licence")
    val licence: String,
    @SerializedName("osm_type")
    val osmType: String,
    @SerializedName("osm_id")
    val osmId: Long,
    @SerializedName("lat")
    val latitude: String,
    @SerializedName("lon")
    val longitude: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("place_rank")
    val placeRank: Int,
    @SerializedName("importance")
    val importance: Double,
    @SerializedName("addresstype")
    val addressType: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("address")
    val address: Address,
    @SerializedName("boundingbox")
    val boundingBox: List<String>
)
