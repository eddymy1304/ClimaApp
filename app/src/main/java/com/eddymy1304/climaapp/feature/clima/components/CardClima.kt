package com.eddymy1304.climaapp.feature.clima.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditLocation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eddymy1304.climaapp.R
import com.eddymy1304.climaapp.ui.model.WeatherDataModel
import com.eddymy1304.climaapp.ui.model.WeatherIcons
import kotlin.math.roundToInt


@Composable
fun CardClima(
    modifier: Modifier = Modifier,
    data: WeatherDataModel,
    onClickIconLocationEdit: () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
        ),
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {

            Box(Modifier.fillMaxWidth()) {

                IconButton(onClick = onClickIconLocationEdit) {
                    Icon(
                        imageVector = Icons.Outlined.EditLocation,
                        contentDescription = null
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    text = "${data.name.orEmpty()}, ${data.sys?.country.orEmpty()}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            val weatherIcon = WeatherIcons.entries.find {
                it.icon == data.weather?.first()?.icon
            }

            Log.d("WeatherIcons", "weatherIcon: $weatherIcon")

            weatherIcon?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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

            data.weather?.first()?.description?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic,
                    text = it.uppercase(),
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 80.sp,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.temp, (data.main?.temp ?: 0.0).roundToInt())
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                text = stringResource(
                    R.string.feels_like,
                    (data.main?.feelsLike ?: 0.0).roundToInt()
                ),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontSize = 16.sp,
                    text = stringResource(R.string.min)
                )
                Text(
                    fontSize = 16.sp,
                    text = stringResource(R.string.temp, (data.main?.tempMin ?: 0.00).roundToInt())
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    fontSize = 16.sp,
                    text = stringResource(R.string.max)
                )
                Text(
                    fontSize = 16.sp,
                    text = stringResource(R.string.temp, (data.main?.tempMax ?: 0.00).roundToInt())
                )
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}
