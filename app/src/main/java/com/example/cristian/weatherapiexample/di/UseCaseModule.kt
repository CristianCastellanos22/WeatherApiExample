package com.example.cristian.weatherapiexample.di

import com.example.cristian.weatherapiexample.domain.repositories.SearchCityRepository
import com.example.cristian.weatherapiexample.domain.useCases.SearchCityUseCase
import com.example.cristian.weatherapiexample.domain.useCases.SearchCityUseCaseImpl
import com.example.cristian.weatherapiexample.domain.useCases.SearchForecastUseCase
import com.example.cristian.weatherapiexample.domain.useCases.SearchForecastUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideSearchCityUseCase(searchCityRepository: SearchCityRepository): SearchCityUseCase {
        return SearchCityUseCaseImpl(searchCityRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideSearchForecastUseCase(searchCityRepository: SearchCityRepository): SearchForecastUseCase {
        return SearchForecastUseCaseImpl(searchCityRepository)
    }
}