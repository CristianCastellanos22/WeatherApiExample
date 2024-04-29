package com.example.cristian.weatherapiexample.data.network

import com.example.cristian.weatherapiexample.BuildConfig
import com.example.cristian.weatherapiexample.data.models.City
import com.example.cristian.weatherapiexample.data.models.ForecastCity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("search.json?key=${BuildConfig.API_KEY}")
    suspend fun getWeather(@Query("q") city: String): Response<List<City>>

    @GET("forecast.json?key=${BuildConfig.API_KEY}")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("days") days: Int = 3,
        @Query("lang") lang: String = "es"
    ): Response<ForecastCity>
}
