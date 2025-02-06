package com.eddymy1304.climaapp.ui.model

data class WeatherForecastModel(
    var date: String? = null ,
    var temp: Double? = null,
    var icon: WeatherIcons? = null,
    var description: String? = null,
    var feelsLike: Double? = null,
    var day: String? = null,
    var list: List<HourlyTempModel>? = null
) {
    data class HourlyTempModel(
        var hour: String? = null,
        var temp: Double? = null,
        var icon: WeatherIcons? = null
    )
}