package com.eddymy1304.climaapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.eddymy1304.climaapp.feature.clima.navigation.ClimaScreen
import com.eddymy1304.climaapp.feature.clima.navigation.climaScreen
import com.eddymy1304.climaapp.feature.clima.navigation.navigateToClimaScreen
import com.eddymy1304.climaapp.feature.search.navigation.navigateToSearchMapScreen
import com.eddymy1304.climaapp.feature.search.navigation.searchMapScreen

@Composable
fun NavHostApp(
    modifier: Modifier,
    navController: NavHostController
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ClimaScreen()
    ) {

        climaScreen { lat, lng, city ->
            navController.navigateToSearchMapScreen(lat, lng, city)
        }

        searchMapScreen { lat, lng ->
            navController.navigateToClimaScreen(lat, lng) {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }

    }

}