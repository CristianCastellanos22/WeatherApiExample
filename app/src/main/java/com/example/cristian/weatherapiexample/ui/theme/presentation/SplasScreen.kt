package com.example.cristian.weatherapiexample.ui.theme.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cristian.weatherapiexample.R
import com.example.cristian.weatherapiexample.ui.theme.presentation.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navHostController.popBackStack()
        navHostController.navigate(Routes.SearchCity.route)
    }
    Splash()
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_200).copy(alpha = 0.4f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.cloudyday),
            contentDescription = "Logo Splash",
        )
        Text(text = stringResource(id = R.string.app_name), color = Color.White, fontSize = 24.sp)
    }
}
