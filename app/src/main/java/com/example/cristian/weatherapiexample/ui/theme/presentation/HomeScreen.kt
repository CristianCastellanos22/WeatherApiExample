package com.example.cristian.weatherapiexample.ui.theme.presentation

import SearchBar
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cristian.weatherapiexample.R
import com.example.cristian.weatherapiexample.domain.models.ForecastCityUI
import com.example.cristian.weatherapiexample.domain.models.ForeseeUI
import com.example.cristian.weatherapiexample.ui.theme.presentation.widgets.ErrorDialog
import com.example.cristian.weatherapiexample.ui.theme.presentation.widgets.LoadingWheel
import com.example.cristian.weatherapiexample.ui.theme.viewmodels.CityViewModel
import com.example.cristian.weatherapiexample.ui.theme.viewmodels.ForecastViewModel
import com.example.cristian.weatherapiexample.ui.theme.viewmodels.ViewState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    forecastViewModel: ForecastViewModel,
    cityViewModel: CityViewModel
) {
    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                cityViewModel = cityViewModel,
                forecastViewModel = forecastViewModel
            )
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.DarkGray.copy(alpha = 0.2f))
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            ObserveStateForecastViewModel(forecastViewModel = forecastViewModel)
            ObserveStateCityViewModel(cityViewModel = cityViewModel)
        }
    }
}

@Composable
private fun ObserveStateForecastViewModel(forecastViewModel: ForecastViewModel) {
    val status by forecastViewModel.status.observeAsState()
    when {
        status is ViewState.Error -> {
            ErrorDialog(messageId = (status as ViewState.Error).exception.toString()) {}
        }

        status is ViewState.Loading -> {
            LoadingWheel()
        }

        status is ViewState.Success<*> -> {
            val forecast = (status as? ViewState.Success<*>)?.data
            if (forecast != null) {
                ShowCity(forecast as ForecastCityUI)
            }

        }
    }
}

@Composable
private fun ObserveStateCityViewModel(cityViewModel: CityViewModel) {
    val status by cityViewModel.status.observeAsState()
    when {
        status is ViewState.Error -> {
            ErrorDialog(messageId = (status as ViewState.Error).exception.toString()) {
                cityViewModel.resetApiResponseStatus()
            }
        }

        status is ViewState.Loading -> {
            LoadingWheel()
        }
    }
}

@Composable
fun ShowCity(forecastCityUI: ForecastCityUI) {
    ElevatedCard(
        modifier = Modifier
            .padding(top = 72.dp, start = 16.dp, end = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                forecastCityUI.location?.name?.let { Text(text = it, fontSize = 24.sp) }
                Text(text = " - ")
                forecastCityUI.location?.region?.let { Text(text = it, fontSize = 24.sp) }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(
                        id = R.string.celsius,
                        forecastCityUI.current?.tempC?.toInt().toString()
                    ),
                    fontSize = 32.sp
                )
                LoadingImageFromUrl(url = forecastCityUI.current?.condition?.icon.toString())
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = forecastCityUI.current?.condition?.text.toString())
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                forecastCityUI.location?.country?.let { Text(text = it) }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                forecastCityUI.forecast?.forecastDay?.forEach {
                    item {
                        ShowForecastDay(it)
                    }
                }
            }
        }
    }
}

@Composable
fun ShowForecastDay(foreseeUI: ForeseeUI) {
    ElevatedCard(
        modifier = Modifier.padding(12.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = foreseeUI.date.toString(),
                    textAlign = TextAlign.Center
                )
                Row {
                    Text(
                        text = stringResource(
                            id = R.string.max,
                            foreseeUI.day?.maxtempC?.toInt().toString()
                        ) + " - "
                    )
                    Text(
                        text = stringResource(
                            id = R.string.min,
                            foreseeUI.day?.mintempC?.toInt().toString()
                        )
                    )
                }
                Text(text = stringResource(id = R.string.avg, foreseeUI.day?.avgtempC?.toInt().toString()))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    foreseeUI.day?.condition?.text?.let { Text(text = it) }
                }
            }
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                LoadingImageFromUrl(url = foreseeUI.day?.condition?.icon.toString(), size = 80)
            }
        }
    }
}

@Composable
fun LoadingImageFromUrl(url: String, size: Int = 64) {
    AsyncImage(
        model = stringResource(id = R.string.path, url), modifier = Modifier
            .size(size.dp)
            .padding(top = 8.dp, start = 8.dp), contentDescription = null
    )
}
