package com.br.streamcontrol.data.remote.service

import com.br.streamcontrol.data.remote.dto.response.LocationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface LocationService {
    @GET("https://nominatim.openstreetmap.org")
    fun getUserLocation(
        @Query("q") query: String,
        @Query("format") format: String = "jsonv2",
        @Query("addressdetails") addressDetails: Int = 1,
        @Query("limit") limit: Int = 1,
    ): Call<List<LocationResponse>>
}