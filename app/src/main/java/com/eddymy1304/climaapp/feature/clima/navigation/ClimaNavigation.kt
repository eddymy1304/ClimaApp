package com.eddymy1304.climaapp.feature.clima.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.eddymy1304.climaapp.feature.clima.ClimaScreen
import kotlinx.serialization.Serializable

@Serializable
data class ClimaScreen(
    var latitude: Double? = null,
    var longitude: Double? = null
)

fun NavController.navigateToClimaScreen(
    latitude: Double?,
    longitude: Double?,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) =
    navigate(ClimaScreen(latitude = latitude, longitude = longitude)) {
        navOptions()
    }

fun NavGraphBuilder.climaScreen(
    navigateToSearchMapScreen: (lat: Double?, lng: Double?, city: String) -> Unit
) {
    composable<ClimaScreen> {
        ClimaScreen(
            navigateToSearchMapScreen = navigateToSearchMapScreen
        )
    }
}