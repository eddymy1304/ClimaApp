package com.eddymy1304.climaapp.feature.clima

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.eddymy1304.climaapp.core.LocationTracker
import com.eddymy1304.climaapp.core.Result.*
import com.eddymy1304.climaapp.data.Repository
import com.eddymy1304.climaapp.feature.clima.navigation.ClimaScreen
import com.eddymy1304.climaapp.ui.mappers.toModel
import com.eddymy1304.climaapp.ui.model.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ClimaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val locationTracker: LocationTracker,
    private val repository: Repository
) : ViewModel() {

    private var _location = MutableStateFlow(
        LocationModel(
            latitude = savedStateHandle.toRoute<ClimaScreen>().latitude,
            longitude = savedStateHandle.toRoute<ClimaScreen>().longitude
        )
    )

    val location = _location.asStateFlow()

    private var lastLocation = LocationModel()

    private val _uiState = MutableStateFlow(UiStateClimaScreen())
    val uiState = _uiState.asStateFlow()

    fun getCurrentWeatherData() {

        val currentLocation = _location.value

        if (lastLocation.latitude != null &&
            lastLocation.longitude != null &&
            lastLocation.latitude == currentLocation.latitude &&
            lastLocation.longitude == currentLocation.longitude
        ) return

        viewModelScope.launch {

            _uiState.update { UiStateClimaScreen(isLoading = true) }

            if (_location.value.latitude == null && _location.value.longitude == null) {
                val locationResponse = withContext(Dispatchers.IO) {
                    locationTracker.getLocation()
                }

                if (locationResponse == null) {
                    _uiState.update { UiStateClimaScreen(error = "Error al obtener la ubicación") }
                    return@launch
                }

                Log.d("location", locationResponse.toString())

                _location.update {
                    _location.value.copy(
                        latitude = locationResponse.latitude,
                        longitude = locationResponse.longitude
                    )
                }
            }

            lastLocation = _location.value

            when (val result =
                repository.getCurrentWeatherData(
                    _location.value.latitude!!,
                    _location.value.longitude!!
                )) {
                is Error -> {
                    _uiState.update {
                        UiStateClimaScreen(
                            error = result.exception?.message ?: "Error"
                        )
                    }
                }

                is Success -> {
                    _uiState.update { UiStateClimaScreen(data = result.data.toModel()) }
                    getWeatherForecast()
                }
            }
        }
    }

    private fun getWeatherForecast() {
        viewModelScope.launch {

            _uiState.update { _uiState.value.copy(loadingList = true) }

            when (val response = repository.get5DayWeatherForecastData(
                _location.value.latitude!!,
                _location.value.longitude!!
            )) {
                is Error -> {
                    _uiState.update {
                        _uiState.value.copy(loadingList = false)
                    }
                }

                is Success -> {
                    _uiState.update {
                        _uiState.value.copy(
                            loadingList = false,
                            forecast = response.data.toModel()
                        )
                    }
                }
            }
        }
    }

    fun showDialogPermission() {
        _uiState.update { UiStateClimaScreen(showDialogPermission = true) }
    }

    fun hideDialogPermission() {
        _uiState.update { UiStateClimaScreen(showDialogPermission = false) }
    }

}