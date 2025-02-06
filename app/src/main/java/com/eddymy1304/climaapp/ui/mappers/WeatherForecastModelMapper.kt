package com.eddymy1304.climaapp.ui.mappers

import android.util.Log
import com.eddymy1304.climaapp.core.Utils
import com.eddymy1304.climaapp.core.average
import com.eddymy1304.climaapp.data.remote.response.FiveDayWeatherForecastDataResponse
import com.eddymy1304.climaapp.ui.model.WeatherForecastModel
import com.eddymy1304.climaapp.ui.model.WeatherIcons

object WeatherForecastModelMapper :
    ModelMapper<FiveDayWeatherForecastDataResponse, List<WeatherForecastModel>> {
    override fun toModel(response: FiveDayWeatherForecastDataResponse): List<WeatherForecastModel> {

        if (response.list.isNullOrEmpty()) return listOf()

        val listFilter = response.list!!.filterNot {
            it.dtTxt != null && Utils.getTodayFormatted() == Utils.getYearMonthDay(it.dtTxt)
        }

        val listGroupByDate = listFilter.groupBy {
            val date = Utils.getYearMonthDay(it.dtTxt)
            date
        }

        Log.d(
            "WeatherForecastModelMapper", """
            listGroupByDate:$listGroupByDate
            """.trimIndent()
        )

        val list = listGroupByDate.map { (date, list) ->

            // promediar temperature por dia
            val averageTemp = list.map { it.main?.temp ?: 0.00 }.average()

            // promediar icono por dia
            val averageIcon = list.map { it.weather?.first()?.icon.orEmpty() }.average()
            val weatherIcon = WeatherIcons.entries.find { averageIcon == it.icon }

            WeatherForecastModel(
                date = date,
                day = Utils.getDayOfWeek(date),
                temp = averageTemp,
                icon = weatherIcon,
                list = list.map { item ->
                    WeatherForecastModel.HourlyTempModel(
                        icon = WeatherIcons.entries.find { it.icon == item.weather?.first()?.icon },
                        temp = item.main?.temp,
                        hour = Utils.formatToHourMinute(item.dtTxt)
                    )
                }
            )
        }

        Log.d(
            "WeatherForecastModelMapper", """
            list Forecaste : $list
        """.trimIndent()
        )

        return list
    }
}

fun FiveDayWeatherForecastDataResponse.toModel() = WeatherForecastModelMapper.toModel(this)