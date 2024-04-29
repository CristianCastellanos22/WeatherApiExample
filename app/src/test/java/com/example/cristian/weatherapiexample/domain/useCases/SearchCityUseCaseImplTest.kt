package com.example.cristian.weatherapiexample.domain.useCases

import com.example.cristian.weatherapiexample.domain.models.CityUI
import com.example.cristian.weatherapiexample.domain.repositories.SearchCityRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchCityUseCaseImplTest {

    // Crea un TestCoroutineDispatcher
    private val testDispatcher = TestCoroutineDispatcher()

    // Crea un SearchCityRepository simulado
    private val searchCityRepository: SearchCityRepository = mockk()

    // Crea un SearchCityUseCaseImpl para las pruebas
    private lateinit var searchCityUseCase: SearchCityUseCaseImpl

    @Before
    fun setUp() {
        searchCityUseCase = SearchCityUseCaseImpl(searchCityRepository)
    }

    @Test
    fun `invoke onSuccess returns Success Result`() = runTest {
        // Configura el SearchCityRepository para devolver una lista de ciudades
        val cities =
            listOf(CityUI(502209, "Bogota", "Cundinamarca", "CO", 4.6, -74.08, "America/Bogota"))
        coEvery { searchCityRepository.searchCity("Bogota") } returns Result.success(cities)

        // Llama a invoke en el SearchCityUseCaseImpl
        val result = searchCityUseCase.invoke("Bogota")

        // Verifica que se devolvió un Result de éxito con la lista de ciudades
        assertEquals(Result.success(cities), result)
    }

    @Test
    fun `invoke onFailure returns Error Result`() = runTest {
        // Configura el SearchCityRepository para lanzar una excepción
        val exception = Exception("Error")
        coEvery { searchCityRepository.searchCity("Bogota") } returns Result.failure(exception)

        // Llama a invoke en el SearchCityUseCaseImpl
        val result = searchCityUseCase.invoke("Bogota")

        // Verifica que se devolvió un Result de error con la excepción
        assertTrue(result.isFailure)
    }

    @Test
    fun `invoke with empty query returns Error Result`() = runTest {
        coEvery { searchCityRepository.searchCity("") } returns Result.failure(Exception("Query vacía"))

        // Llama a invoke en el SearchCityUseCaseImpl con una consulta vacía
        val result = searchCityUseCase.invoke("")

        // Verifica que se devolvió un Result de error
        assertTrue(result.isFailure)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
    }
}