package com.eddymy1304.climaapp.ui.model


data class WeatherDataModel(
    var base: String? = null,
    var clouds: CloudsModel? = null,
    var cod: Int? = null,
    var coord: CoordModel? = null,
    var dt: Int? = null,
    var id: Int? = null,
    var main: MainModel? = null,
    var name: String? = null,
    var rain: RainModel? = null,
    var sys: SysModel? = null,
    var timezone: Int? = null,
    var visibility: Int? = null,
    var weather: List<WeatherModel?>? = null,
    var wind: WindModel? = null
) {

    data class CloudsModel(
        var all: Int? = null
    )

    data class CoordModel(
        var lat: Double? = null,
        var lon: Double? = null
    )

    data class MainModel(
        var feelsLike: Double? = null,
        var grandLevel: Int? = null,
        var humidity: Int? = null,
        var pressure: Int? = null,
        var seaLevel: Int? = null,
        var temp: Double? = null,
        var tempMax: Double? = null,
        var tempMin: Double? = null
    )

    data class RainModel(var oneH: Double? = null)

    data class SysModel(
        var country: String? = null,
        var id: Int? = null,
        var sunrise: Int? = null,
        var sunset: Int? = null,
        var type: Int? = null
    )

    data class WeatherModel(
        var description: String? = null,
        var icon: String? = null,
        var id: Int? = null,
        var main: String? = null
    )

    data class WindModel(
        var deg: Int? = null,
        var gust: Double? = null,
        var speed: Double? = null
    )

}