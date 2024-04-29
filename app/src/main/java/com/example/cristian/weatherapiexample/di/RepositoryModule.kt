package com.example.cristian.weatherapiexample.di

import com.example.cristian.weatherapiexample.data.network.WeatherApiService
import com.example.cristian.weatherapiexample.data.repositories.SearchCityRepositoryImpl
import com.example.cristian.weatherapiexample.di.DispatchersModule_ProvideDispatchersFactory.provideDispatchers
import com.example.cristian.weatherapiexample.domain.repositories.SearchCityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideSearchCityRepository(
        weatherApiService: WeatherApiService,
    ): SearchCityRepository {
        return SearchCityRepositoryImpl(weatherApiService, provideDispatchers())
    }
}