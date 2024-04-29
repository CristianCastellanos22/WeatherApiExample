package com.example.cristian.weatherapiexample.domain.useCases

import com.example.cristian.weatherapiexample.domain.models.CityUI

interface SearchCityUseCase {
    suspend fun invoke(query: String): Result<List<CityUI>>
}