package com.eddymy1304.climaapp.ui.model

import androidx.annotation.DrawableRes
import com.eddymy1304.climaapp.R

enum class WeatherIcons(
    val icon: String,
    @DrawableRes val drawable: Int
) {
    WEATHER_01D(
        icon = "01d",
        drawable = R.drawable.weather_01d
    ),
    WEATHER_02D(
        icon = "02d",
        drawable = R.drawable.weather_02d
    ),
    WEATHER_03D(
        icon = "03d",
        drawable = R.drawable.weather_03d
    ),
    WEATHER_04D(
        icon = "04d",
        drawable = R.drawable.weather_04d
    ),
    WEATHER_09D(
        icon = "09d",
        drawable = R.drawable.weather_09d
    ),
    WEATHER_10D(
        icon = "10d",
        drawable = R.drawable.weather_10d
    ),
    WEATHER_11D(
        icon = "11d",
        drawable = R.drawable.weather_11d
    ),
    WEATHER_13D(
        icon = "13d",
        drawable = R.drawable.weather_13d
    ),
    WEATHER_50D(
        icon = "50d",
        drawable = R.drawable.weather_50d
    ),
    WEATHER_01N(
        icon = "01n",
        drawable = R.drawable.weather_01n
    ),
    WEATHER_02N(
        icon = "02n",
        drawable = R.drawable.weather_02n
    ),
    WEATHER_03N(
        icon = "03n",
        drawable = R.drawable.weather_03n
    ),
    WEATHER_04N(
        icon = "04n",
        drawable = R.drawable.weather_04n
    ),
    WEATHER_09N(
        icon = "09n",
        drawable = R.drawable.weather_09n
    ),
    WEATHER_10N(
        icon = "10n",
        drawable = R.drawable.weather_10n
    ),
    WEATHER_11N(
        icon = "11n",
        drawable = R.drawable.weather_11n
    ),
    WEATHER_13N(
        icon = "13n",
        drawable = R.drawable.weather_13n
    ),
    WEATHER_50N(
        icon = "50n",
        drawable = R.drawable.weather_50n
    )
}