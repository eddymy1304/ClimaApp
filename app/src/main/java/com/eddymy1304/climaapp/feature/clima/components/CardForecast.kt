package com.eddymy1304.climaapp.feature.clima.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eddymy1304.climaapp.R
import com.eddymy1304.climaapp.ui.model.WeatherForecastModel
import com.eddymy1304.climaapp.ui.model.WeatherIcons
import com.eddymy1304.climaapp.ui.theme.ClimaAppTheme
import kotlin.math.roundToInt

@Composable
fun CardForecast(
    modifier: Modifier = Modifier,
    data: WeatherForecastModel
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
        ),
        modifier = modifier
            .width(120.dp)
            .heightIn(min = 320.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Text(
                maxLines = 1,
                textAlign = TextAlign.Center,
                text = data.day.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            data.icon?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(40.dp),
                        painter = painterResource(id = it.drawable),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.temp, (data.temp ?: 0.00).roundToInt()),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            data.list?.let { list ->
                list.forEach { item ->
                    RowTempHourly(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        hourlyTemp = item
                    )
                }
            }
        }
    }
}

@Composable
fun RowTempHourly(
    modifier: Modifier = Modifier,
    hourlyTemp: WeatherForecastModel.HourlyTempModel
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            maxLines = 1,
            fontSize = 12.sp,
            text = hourlyTemp.hour.orEmpty()
        )

        Row {
            Text(
                maxLines = 1,
                fontSize = 12.sp,
                text = stringResource(R.string.temp, (hourlyTemp.temp ?: 0.00).roundToInt())
            )
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (hourlyTemp.icon == null) Spacer(Modifier)
                else Icon(
                    painter = painterResource(id = hourlyTemp.icon!!.drawable),
                    contentDescription = null
                )
            }
        }
    }

}

@Preview
@Composable
fun CardForecastPreview() {
    ClimaAppTheme {
        CardForecast(
            data = WeatherForecastModel(
                date = "2025-02-08",
                icon = WeatherIcons.WEATHER_01D,
                temp = 33.40,
                day = "07 Feb",
                list = listOf(
                    WeatherForecastModel.HourlyTempModel(
                        icon = WeatherIcons.WEATHER_01D,
                        temp = 33.40,
                        hour = "07:00"
                    ),
                    WeatherForecastModel.HourlyTempModel(
                        icon = WeatherIcons.WEATHER_01D,
                        temp = 33.40,
                        hour = "07:00"
                    ),
                    WeatherForecastModel.HourlyTempModel(
                        icon = WeatherIcons.WEATHER_01D,
                        temp = 35.40,
                        hour = "10:00"
                    )
                )
            )
        )
    }
}
