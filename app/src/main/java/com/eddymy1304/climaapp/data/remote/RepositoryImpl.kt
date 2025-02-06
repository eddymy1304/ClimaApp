package com.eddymy1304.climaapp.data.remote

import com.eddymy1304.climaapp.core.Result
import com.eddymy1304.climaapp.core.handleResult
import com.eddymy1304.climaapp.data.Repository
import com.eddymy1304.climaapp.data.remote.response.FiveDayWeatherForecastDataResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiService
) : Repository {

    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ) = handleResult {
        api.getCurrentWeatherData(latitude = latitude, longitude = longitude)
    }

    override suspend fun get5DayWeatherForecastData(
        latitude: Double,
        longitude: Double
    ): Result<FiveDayWeatherForecastDataResponse> {
        return handleResult {
            api.get5DayWeatherForecastData(latitude = latitude, longitude = longitude)
        }
    }

}