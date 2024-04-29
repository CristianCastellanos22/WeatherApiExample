package com.example.cristian.weatherapiexample.ui.theme.presentation.navigation

sealed class Routes(val route: String) {
    object SearchCity : Routes("searchCity")
    object Splash : Routes("splashScreen")

}
