package com.example.cristian.weatherapiexample.data.repositories

import com.example.cristian.weatherapiexample.data.mappers.toCityUI
import com.example.cristian.weatherapiexample.data.mappers.toForecastCityUI
import com.example.cristian.weatherapiexample.data.network.WeatherApiService
import com.example.cristian.weatherapiexample.data.network.resultOf
import com.example.cristian.weatherapiexample.domain.models.CityUI
import com.example.cristian.weatherapiexample.domain.models.ForecastCityUI
import com.example.cristian.weatherapiexample.domain.repositories.SearchCityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchCityRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val dispatcher: CoroutineDispatcher
) : SearchCityRepository {

    override suspend fun searchCity(query: String): Result<List<CityUI>> = resultOf {
        val result = withContext(dispatcher) {
            apiService.getWeather(query)
        }
        val body = result.body()

        if (result.isSuccessful && body != null) {
            body.map { it.toCityUI() }
        } else {
            val errorMessage = result.errorBody().toString()
            error(errorMessage)
        }
    }

    override suspend fun searchForecast(query: String): Result<ForecastCityUI> = resultOf {
        val result = withContext(dispatcher) {
            apiService.getForecast(query)
        }
        val body = result.body()

        if (result.isSuccessful && body != null) {
            body.toForecastCityUI()
        } else {
            val errorMessage = result.errorBody().toString()
            error(errorMessage)
        }
    }
}
