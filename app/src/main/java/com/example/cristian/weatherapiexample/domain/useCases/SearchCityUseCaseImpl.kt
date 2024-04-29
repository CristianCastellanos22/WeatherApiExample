package com.example.cristian.weatherapiexample.domain.useCases

import com.example.cristian.weatherapiexample.domain.models.CityUI
import com.example.cristian.weatherapiexample.domain.repositories.SearchCityRepository
import javax.inject.Inject

class SearchCityUseCaseImpl @Inject constructor (private val searchCityRepository: SearchCityRepository) :
    SearchCityUseCase {
    override suspend fun invoke(query: String): Result<List<CityUI>> {
        return searchCityRepository.searchCity(query)
    }
}