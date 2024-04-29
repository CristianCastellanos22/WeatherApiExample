package com.example.cristian.weatherapiexample.domain.repositories

import com.example.cristian.weatherapiexample.domain.models.CityUI
import com.example.cristian.weatherapiexample.domain.models.ForecastCityUI

interface SearchCityRepository {
    suspend fun searchCity(query: String): Result<List<CityUI>>

    suspend fun searchForecast(query: String): Result<ForecastCityUI>
}
