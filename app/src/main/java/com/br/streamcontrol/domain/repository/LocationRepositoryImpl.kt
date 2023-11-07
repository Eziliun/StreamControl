package com.br.streamcontrol.domain.repository

import android.util.Log
import com.br.streamcontrol.data.remote.dto.response.LocationResponse
import com.br.streamcontrol.data.remote.infra.ApiServiceFactory
import com.br.streamcontrol.data.remote.service.LocationService
import com.br.streamcontrol.data.remote.state.StateInfo
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private var service: LocationService
) : LocationRepository {

    override suspend fun getLocation(query: String): StateInfo<List<LocationResponse>> {
        return try {
            val call = service.getUserLocation(query)
            val response = call.execute()

            if (response.isSuccessful) {
                val body = response.body()
                if (!body.isNullOrEmpty()) {
                    StateInfo.Success(body)
                } else {
                    StateInfo.Error("Response body is null")
                }
            } else {
                StateInfo.Error("API call failed with code ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("LocationRepositoryImpl", "Erro ao buscar a localização do usuário", e)
            StateInfo.Error("Falha ao buscar a localização do usuário: ${e.message}")
        }
    }
}