package com.eddymy1304.climaapp.feature.clima

import com.eddymy1304.climaapp.ui.model.WeatherDataModel
import com.eddymy1304.climaapp.ui.model.WeatherForecastModel

data class UiStateClimaScreen(
    val isLoading: Boolean = false,
    val data: WeatherDataModel? = null,
    val forecast: List<WeatherForecastModel>? = null,
    val loadingList: Boolean = false,
    val error: String? = null,
    val showDialogPermission: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLocationEnabled: Boolean = false
)
