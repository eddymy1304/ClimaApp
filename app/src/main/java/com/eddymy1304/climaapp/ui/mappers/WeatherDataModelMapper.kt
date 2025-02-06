package com.eddymy1304.climaapp.ui.mappers

import com.eddymy1304.climaapp.data.remote.response.CurrentWeatherDataResponse
import com.eddymy1304.climaapp.ui.model.WeatherDataModel

object WeatherDataModelMapper : ModelMapper<CurrentWeatherDataResponse, WeatherDataModel> {
    override fun toModel(response: CurrentWeatherDataResponse): WeatherDataModel {
        return WeatherDataModel(
            base = response.base,
            clouds = WeatherDataModel.CloudsModel(all = response.clouds?.all),
            cod = response.cod,
            coord = WeatherDataModel.CoordModel(
                lat = response.coord?.lat,
                lon = response.coord?.lon
            ),
            dt = response.dt,
            id = response.id,
            main = WeatherDataModel.MainModel(
                feelsLike = response.main?.feelsLike,
                grandLevel = response.main?.grandLevel,
                humidity = response.main?.humidity,
                pressure = response.main?.pressure,
                seaLevel = response.main?.seaLevel,
                temp = response.main?.temp,
                tempMax = response.main?.tempMax,
                tempMin = response.main?.tempMin
            ),
            name = response.name,
            rain = WeatherDataModel.RainModel(
                oneH = response.rain?.oneH
            ),
            sys = WeatherDataModel.SysModel(
                country = response.sys?.country,
                id = response.sys?.id,
                sunrise = response.sys?.sunrise,
                sunset = response.sys?.sunset,
                type = response.sys?.type
            ),
            timezone = response.timezone,
            visibility = response.visibility,
            weather = response.weather?.map {
                WeatherDataModel.WeatherModel(
                    description = it?.description,
                    icon = it?.icon,
                    id = it?.id,
                    main = it?.main
                )
            },
            wind = WeatherDataModel.WindModel(
                deg = response.wind?.deg,
                gust = response.wind?.gust,
                speed = response.wind?.speed
            )
        )
    }
}

fun CurrentWeatherDataResponse.toModel() = WeatherDataModelMapper.toModel(this)