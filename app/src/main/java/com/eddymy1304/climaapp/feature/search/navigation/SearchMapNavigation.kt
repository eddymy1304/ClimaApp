package com.eddymy1304.climaapp.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.eddymy1304.climaapp.feature.search.SearchMapScreen
import kotlinx.serialization.Serializable

@Serializable
data class SearchMapScreen(
    val latitude: Double?,
    val longitude: Double?,
    val city: String
)

fun NavController.navigateToSearchMapScreen(
    latitude: Double?,
    longitude: Double?,
    city: String,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) =
    navigate(SearchMapScreen(latitude = latitude, longitude = longitude, city = city)) {
        navOptions()
    }

fun NavGraphBuilder.searchMapScreen(
    navigateToClimaScreen: (latitude: Double, longitude: Double) -> Unit
) {
    composable<SearchMapScreen> {
        SearchMapScreen(navigateToClimaScreen = navigateToClimaScreen)
    }
}