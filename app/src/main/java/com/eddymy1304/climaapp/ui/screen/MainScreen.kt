package com.eddymy1304.climaapp.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.eddymy1304.climaapp.ui.navigation.NavHostApp
import com.eddymy1304.climaapp.ui.theme.ClimaAppTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    ClimaAppTheme {

        Scaffold(
            modifier = modifier,
            topBar = {

            }
        ) { padding ->
            NavHostApp(
                modifier = Modifier.padding(padding),
                navController = navController
            )
        }

    }
}

