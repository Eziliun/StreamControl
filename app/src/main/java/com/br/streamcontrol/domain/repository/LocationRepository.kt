package com.br.streamcontrol.domain.repository

import com.br.streamcontrol.data.remote.dto.response.LocationResponse
import com.br.streamcontrol.data.remote.state.StateInfo

interface LocationRepository {
    suspend fun getLocation(query: String) : StateInfo<List<LocationResponse>>
}