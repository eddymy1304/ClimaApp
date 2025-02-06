package com.eddymy1304.climaapp.data

import com.eddymy1304.climaapp.core.Result
import com.eddymy1304.climaapp.data.remote.response.CurrentWeatherDataResponse
import com.eddymy1304.climaapp.data.remote.response.FiveDayWeatherForecastDataResponse

interface Repository {

    suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): Result<CurrentWeatherDataResponse>

    suspend fun get5DayWeatherForecastData(
        latitude: Double,
        longitude: Double
    ): Result<FiveDayWeatherForecastDataResponse>

}