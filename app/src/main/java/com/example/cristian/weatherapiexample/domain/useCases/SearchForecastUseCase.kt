package com.example.cristian.weatherapiexample.domain.useCases

import com.example.cristian.weatherapiexample.domain.models.ForecastCityUI

interface SearchForecastUseCase {
    suspend fun invoke(query: String): Result<ForecastCityUI>
}