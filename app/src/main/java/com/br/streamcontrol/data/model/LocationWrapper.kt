package com.br.streamcontrol.data.model

import com.br.streamcontrol.data.remote.dto.response.LocationResponse

data class LocationResponseWrapper(
    val locations: List<LocationResponse>
)