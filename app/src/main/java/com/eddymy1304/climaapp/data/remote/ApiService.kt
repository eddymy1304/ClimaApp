package com.eddymy1304.climaapp.data.remote

import com.eddymy1304.climaapp.data.remote.response.CurrentWeatherDataResponse
import com.eddymy1304.climaapp.data.remote.response.FiveDayWeatherForecastDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String = "metric",
        @Query("lang") language: String = "es"
    ): Response<CurrentWeatherDataResponse>

    @GET("forecast")
    suspend fun get5DayWeatherForecastData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String = "metric",
        @Query("lang") language: String = "es"
    ): Response<FiveDayWeatherForecastDataResponse>

}