package com.eddymy1304.climaapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class FiveDayWeatherForecastDataResponse(
    var cod: String? = null,
    var message: Int? = null,
    var cnt: Int? = null,
    var list: List<WeatherForecastResponse>? = null,
    var city: CityWeatherForecastResponse? = null
)