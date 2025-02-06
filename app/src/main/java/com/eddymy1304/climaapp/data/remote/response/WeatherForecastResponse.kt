package com.eddymy1304.climaapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
    var clouds: Clouds? = null,
    var dt: Int? = null,
    @SerialName("dt_txt") var dtTxt: String? = null,
    var main: Main? = null,
    var pop: Double? = null,
    var rain: Rain? = null,
    var sys: Sys? = null,
    var visibility: Int? = null,
    var weather: List<Weather?>? = null,
    var wind: Wind? = null
) {
    @Serializable
    data class Clouds(
        var all: Int? = null
    )

    @Serializable
    data class Main(
        @SerialName("feels_like") var feelsLike: Double? = null,
        @SerialName("grnd_level") var grandLevel: Int? = null,
        var humidity: Int? = null,
        var pressure: Int? = null,
        @SerialName("sea_level") var seaLevel: Int? = null,
        var temp: Double? = null,
        @SerialName("temp_kf") var tempKf: Double? = null,
        @SerialName("temp_max") var tempMax: Double? = null,
        @SerialName("temp_min") var tempMin: Double? = null
    )

    @Serializable
    data class Rain(
        @SerialName("3h") var threeH: Double? = null
    )

    @Serializable
    data class Sys(
        var pod: String? = null
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