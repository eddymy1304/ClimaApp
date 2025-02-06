package com.eddymy1304.climaapp.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eddymy1304.climaapp.R
import com.eddymy1304.climaapp.ui.theme.ClimaAppTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun SearchMapScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchMapViewModel = hiltViewModel(),
    navigateToClimaScreen: (latitude: Double, longitude: Double) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchMapScreen(
        modifier = modifier,
        latLng = uiState.latLng ?: LatLng(0.00, 0.00),
        city = uiState.city,
        onMapClick = { latLng -> viewModel.setLatLng(latLng) }
    ) {
        navigateToClimaScreen(
            uiState.latLng?.latitude ?: 0.00,
            uiState.latLng?.longitude ?: 0.00
        )
    }
}

@Composable
fun SearchMapScreen(
    modifier: Modifier = Modifier,
    latLng: LatLng,
    city: String,
    onMapClick: (latLng: LatLng) -> Unit,
    onClickButton: () -> Unit
) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 10f)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = onMapClick
        ) {
            Marker(
                state = MarkerState(position = latLng),
                title = stringResource(R.string.current_location),
                snippet = "Lat: ${latLng.latitude}, Lng: ${latLng.longitude}"
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .background(color = Color.White.copy(alpha = 0.6f))
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onValueChange = {},
            value = city,
            singleLine = true
        )

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            onClick = onClickButton
        ) {
            Text(
                text = stringResource(id = R.string.update_location),
                maxLines = 1
            )
        }
    }

}

@Preview
@Composable
fun SearchMapScreenPreview() {
    ClimaAppTheme {
        SearchMapScreen(
            latLng = LatLng(0.00, 0.00),
            city = "Paita",
            onMapClick = {},
        ) {

        }
    }
}