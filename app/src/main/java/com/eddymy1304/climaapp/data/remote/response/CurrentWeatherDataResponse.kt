package com.eddymy1304.climaapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDataResponse(
    var base: String? = null,
    var clouds: Clouds? = null,
    var cod: Int? = null,
    var coord: Coord? = null,
    var dt: Int? = null,
    var id: Int? = null,
    var main: Main? = null,
    var name: String? = null,
    var rain: Rain? = null,
    var sys: Sys? = null,
    var timezone: Int? = null,
    var visibility: Int? = null,
    var weather: List<Weather?>? = null,
    var wind: Wind? = null


) {

    @Serializable
    data class Clouds(
        var all: Int? = null
    )

    @Serializable
    data class Coord(
        var lat: Double? = null,
        var lon: Double? = null
    )

    @Serializable
    data class Main(
        @SerialName("feels_like") var feelsLike: Double? = null,
        @SerialName("grnd_level") var grandLevel: Int? = null,
        var humidity: Int? = null,
        var pressure: Int? = null,
        @SerialName("sea_level") var seaLevel: Int? = null,
        var temp: Double? = null,
        @SerialName("temp_max") var tempMax: Double? = null,
        @SerialName("temp_min") var tempMin: Double? = null
    )

    @Serializable
    data class Rain(
        @SerialName("1h") var oneH: Double? = null
    )

    @Serializable
    data class Sys(
        var country: String? = null,
        var id: Int? = null,
        var sunrise: Int? = null,
        var sunset: Int? = null,
        var type: Int? = null
    )

    @Serializable
    data class Weather(
        var description: String? = null,
        var icon: String? = null,
        var id: Int? = null,
        var main: String? = null
    )

    @Serializable
    data class Wind(
        var deg: Int? = null,
        var gust: Double? = null,
        var speed: Double? = null
    )

}