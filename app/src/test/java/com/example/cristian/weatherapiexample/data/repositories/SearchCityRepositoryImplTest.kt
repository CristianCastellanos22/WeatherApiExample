package com.example.cristian.weatherapiexample.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cristian.weatherapiexample.data.models.City
import com.example.cristian.weatherapiexample.data.models.Current
import com.example.cristian.weatherapiexample.data.models.ForecastCity
import com.example.cristian.weatherapiexample.data.models.Location
import com.example.cristian.weatherapiexample.data.network.WeatherApiService
import com.example.cristian.weatherapiexample.domain.models.CityUI
import com.example.cristian.weatherapiexample.domain.models.CurrentUI
import com.example.cristian.weatherapiexample.domain.models.ForecastCityUI
import com.example.cristian.weatherapiexample.domain.models.LocationUI
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class SearchCityRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val mockApiService: WeatherApiService = mockk()
    private lateinit var searchCityRepository: SearchCityRepositoryImpl
    private val testScope = TestCoroutineScope(testDispatcher)


    @Before
    fun setUp() {
        searchCityRepository = SearchCityRepositoryImpl(mockApiService, testDispatcher)
    }

    @Test
    fun `searchCity with valid query should return success`() = testScope.runTest {
        // Given
        val query = "Bogota"
        val cities =
            listOf(City(502209, "Bogota", "Cundinamarca", "CO", 4.6, -74.08, "America/Bogota"))

        coEvery { mockApiService.getWeather(query) } returns (Response.success(cities))

        // When
        val result = searchCityRepository.searchCity(query)
        val citiesUI =
            cities.map { CityUI(it.id, it.name, it.region, it.country, it.lat, it.lon, it.url) }

        // Then
        assertEquals(Result.success(citiesUI), result)
    }

    @Test
    fun `searchForecast with valid query should return success`() = testScope.runTest {
        // Given
        val query = "Bogota"
        val cities =
            ForecastCity(
                Location("", "", "", 0.0, 0.0, "", ""),
                Current(0.0, 0.0, null, 0.0, 0, 0),
                null
            )

        coEvery { mockApiService.getForecast(query) } returns (Response.success(cities))

        // When
        val result = searchCityRepository.searchForecast(query)
        val forecastCityUI = ForecastCityUI(
            LocationUI("", "", "", 0.0, 0.0, "", ""),
            CurrentUI(0.0, 0.0, null, 0.0, 0, 0),
            null
        )

        // Then
        assertEquals(Result.success(forecastCityUI), result)
    }

    @Test
    fun `searchCity with valid query should return success with empty list`() =
        testScope.runTest {
            // Given
            val query = "Bogota"
            val cities = emptyList<City>()

            coEvery { mockApiService.getWeather(query) } returns (Response.success(cities))

            // When
            val result = searchCityRepository.searchCity(query)

            // Then
            assertEquals(Result.success(emptyList<CityUI>()), result)
        }

    @Test
    fun `searchCity onFailure returns Error Result`() = runTest {
        // Configura el WeatherApiService para lanzar una excepción
        val exception = Exception("Error")
        coEvery { mockApiService.getWeather("Bogota") } throws exception

        // Llama a searchCity en el SearchCityRepositoryImpl
        val result = searchCityRepository.searchCity("Bogota")

        // Verifica que se devolvió un Result de error con la excepción
        assertTrue(result.isFailure)
    }

    @Test
    fun `searchForecast onFailure returns Error Result`() = runTest {
        // Configura el WeatherApiService para lanzar una excepción
        val exception = Exception("Error")
        coEvery { mockApiService.getForecast("Bogota") } throws exception

        // Llama a searchCity en el SearchCityRepositoryImpl
        val result = searchCityRepository.searchForecast("Bogota")

        // Verifica que se devolvió un Result de error con la excepción
        assertTrue(result.isFailure)
    }

    @Test
    fun `searchCity with empty query returns Error Result`() = runTest {
        // Llama a searchCity en el SearchCityRepositoryImpl con una consulta vacía
        val result = searchCityRepository.searchCity("")

        // Verifica que se devolvió un Result de error
        assertTrue(result.isFailure)
    }

    @Test
    fun `searchForecast with empty query returns Error Result`() = runTest {
        // Llama a searchCity en el SearchCityRepositoryImpl con una consulta vacía
        val result = searchCityRepository.searchForecast("")

        // Verifica que se devolvió un Result de error
        assertTrue(result.isFailure)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }
}