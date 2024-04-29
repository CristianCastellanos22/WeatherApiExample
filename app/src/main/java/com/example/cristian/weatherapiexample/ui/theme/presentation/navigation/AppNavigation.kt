package com.example.cristian.weatherapiexample.ui.theme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cristian.weatherapiexample.ui.theme.presentation.HomeScreen
import com.example.cristian.weatherapiexample.ui.theme.presentation.SplashScreen
import com.example.cristian.weatherapiexample.ui.theme.viewmodels.CityViewModel
import com.example.cristian.weatherapiexample.ui.theme.viewmodels.ForecastViewModel

@Composable
fun setupNavHost(
    forecastViewModel: ForecastViewModel,
    cityViewModel: CityViewModel
) {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController, startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(navigationController)
        }
        composable(Routes.SearchCity.route) {
            HomeScreen(
                forecastViewModel = forecastViewModel,
                cityViewModel = cityViewModel
            )
        }
    }
}
