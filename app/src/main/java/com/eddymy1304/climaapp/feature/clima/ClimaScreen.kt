package com.eddymy1304.climaapp.feature.clima

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eddymy1304.climaapp.R
import com.eddymy1304.climaapp.feature.clima.components.CardClima
import com.eddymy1304.climaapp.feature.clima.components.CardForecast
import com.eddymy1304.climaapp.ui.components.LoadingDialog
import com.eddymy1304.climaapp.ui.model.WeatherDataModel
import com.eddymy1304.climaapp.ui.theme.Blue
import com.eddymy1304.climaapp.ui.theme.BlueGray
import com.eddymy1304.climaapp.ui.theme.ClimaAppTheme
import com.eddymy1304.climaapp.ui.theme.Gray
import com.eddymy1304.climaapp.ui.theme.LightOrange
import com.eddymy1304.climaapp.ui.theme.LightSkyBlue
import com.eddymy1304.climaapp.ui.theme.LightYellow
import com.eddymy1304.climaapp.ui.theme.Orange
import com.eddymy1304.climaapp.ui.theme.SkyBlue
import com.eddymy1304.climaapp.ui.theme.Yellow
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@SuppressLint("PermissionLauncherDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ClimaScreen(
    modifier: Modifier = Modifier,
    viewModel: ClimaViewModel = hiltViewModel(),
    navigateToSearchMapScreen: (
        latitude: Double,
        longitude: Double,
        city: String
    ) -> Unit
) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val location by viewModel.location.collectAsStateWithLifecycle()

    val listPermission = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val permissionState = rememberMultiplePermissionsState(listPermission)

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        RequestMultiplePermissions()
    ) { granters ->
        if (granters.all { it.value }) viewModel.getCurrentWeatherData()
        else {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.permission_denied),
                    Toast.LENGTH_SHORT
                )
                .show()
        }

    }

    LaunchedEffect(Unit) {
        if (!permissionState.allPermissionsGranted && permissionState.shouldShowRationale)
            viewModel.showDialogPermission()
        else requestPermissionLauncher.launch(listPermission.toTypedArray())
    }

    ClimaScreen(
        modifier = modifier,
        uiState = uiState,
        onClickConfirmButtonDialog = {
            viewModel.hideDialogPermission()
            requestPermissionLauncher.launch(listPermission.toTypedArray())
        },
        onClickIconLocationEdit = {
            navigateToSearchMapScreen(
                location.latitude ?: 0.00,
                location.longitude ?: 0.00,
                uiState.data?.name ?: ""
            )
        }
    )
}

@Composable
fun ClimaScreen(
    modifier: Modifier = Modifier,
    uiState: UiStateClimaScreen,
    onClickConfirmButtonDialog: () -> Unit,
    onClickIconLocationEdit: () -> Unit
) {

    val temp = uiState.data?.main?.temp ?: 0.00

    val backgroundListColor = when {
        temp > 10.00 && temp <= 25.00 -> listOf(
            Blue,
            SkyBlue,
            LightSkyBlue
        )

        temp > 25.00 -> listOf(
            Orange,
            LightOrange,
            LightYellow,
            Yellow
        )

        else -> listOf(
            SkyBlue,
            BlueGray,
            Gray
        )
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = backgroundListColor,
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
    ) {

        if (!uiState.error.isNullOrBlank()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = Icons.Outlined.ErrorOutline,
                    contentDescription = null,
                    tint = Color.Red.copy(alpha = 0.2f),
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                )
                Text(
                    text = uiState.error,
                    color = Color.Red,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 4
                )
            }
        }

        if (uiState.isLoading) {
            LoadingDialog()
        } else {
            uiState.data?.let {
                CardClima(
                    modifier = Modifier
                        .padding(16.dp)
                        .heightIn(min = 360.dp),
                    data = uiState.data,
                    onClickIconLocationEdit = onClickIconLocationEdit
                )
            }

            if (uiState.loadingList) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    CircularProgressIndicator()
                }
            } else {
                uiState.forecast?.let { list ->
                    LazyRow(
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(list, key = { it.date.orEmpty() }) { forecast ->
                            CardForecast(data = forecast)
                        }
                    }
                }
            }
        }

        if (uiState.showDialogPermission) {
            AlertDialog(
                icon = { Icon(imageVector = Icons.Outlined.Warning, contentDescription = null) },
                text = { Text(text = stringResource(R.string.message_permission)) },
                onDismissRequest = {},
                confirmButton = {
                    TextButton(
                        onClick = onClickConfirmButtonDialog,
                        content = { Text(text = stringResource(R.string.accept)) }
                    )
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ClimaScreenPreview() {
    ClimaAppTheme {
        ClimaScreen(
            uiState = UiStateClimaScreen(
                data = WeatherDataModel(
                    name = "Paita",
                    main = WeatherDataModel.MainModel(
                        temp = 24.83,
                        feelsLike = 25.2,
                        tempMin = 24.83,
                        tempMax = 24.83,
                        pressure = 1008,
                        humidity = 70,
                        seaLevel = 1008,
                        grandLevel = 999
                    ),
                    sys = WeatherDataModel.SysModel(
                        country = "PE"
                    )
                )
            ),
            onClickConfirmButtonDialog = {},
            onClickIconLocationEdit = {}
        )
    }
}