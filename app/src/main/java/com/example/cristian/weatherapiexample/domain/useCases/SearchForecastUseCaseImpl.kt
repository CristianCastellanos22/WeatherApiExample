package com.example.cristian.weatherapiexample.domain.useCases

import com.example.cristian.weatherapiexample.domain.models.ForecastCityUI
import com.example.cristian.weatherapiexample.domain.repositories.SearchCityRepository
import javax.inject.Inject

class SearchForecastUseCaseImpl @Inject constructor(
    private val searchCityRepository: SearchCityRepository
) : SearchForecastUseCase {
    override suspend fun invoke(query: String): Result<ForecastCityUI> {
        return searchCityRepository.searchForecast(query)
    }
}