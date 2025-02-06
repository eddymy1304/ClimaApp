package com.eddymy1304.climaapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherForecastResponse(
    var coord: Coord? = null,
    var country: String? = null,
    var id: Int? = null,
    var name: String? = null,
    var population: Int? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null,
    var timezone: Int? = null
) {

    @Serializable
    data class Coord(
        var lat: Double? = null,
        var lon: Double? = null
    )
}