package com.br.streamcontrol.domain.viewmodel

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.br.streamcontrol.data.remote.dto.response.LocationResponse
import com.br.streamcontrol.data.remote.state.StateInfo
import com.br.streamcontrol.domain.repository.LocationRepositoryImpl
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    application: Application,
    private val repository: LocationRepositoryImpl
) : AndroidViewModel(application) {
    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    val locationData: MutableState<Location?> = mutableStateOf(null)

    private val _locationResult = MutableLiveData<StateInfo<List<LocationResponse>>>()
    val locationResult: LiveData<StateInfo<List<LocationResponse>>> = _locationResult

    val cityLiveData = MutableLiveData<String>()

    fun requestLocation() {
        if (ContextCompat.checkSelfPermission(
                getApplication(),
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    locationData.value = location
                    getCurrentLocation("${location?.latitude} ${location?.longitude}")
                }
        }
    }


    private fun getCurrentLocation(query: String) {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.e("ERRO Location ", "$throwable")
        }) {
            val state = repository.getLocation(query)
            withContext(Dispatchers.Main) {
                _locationResult.value = state

                if (state is StateInfo.Success) {
                    val locationResponseList = state.data
                    if (locationResponseList!!.isNotEmpty()) {
                        val firstLocationResponse = locationResponseList.first()
                        val address = firstLocationResponse.address
                        val city = address.city
                        val addressState = firstLocationResponse.address.state
                        val country = firstLocationResponse.address.country
                        val location = "$city, $addressState, $country"
                        cityLiveData.postValue(location)
                    }
                }
            }
        }
    }
}
