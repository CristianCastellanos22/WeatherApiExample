package com.example.cristian.weatherapiexample.ui.theme.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cristian.weatherapiexample.domain.models.CityUI
import com.example.cristian.weatherapiexample.domain.useCases.SearchCityUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private val searchCityUseCase = mockk<SearchCityUseCase>()
    private val viewModel = CityViewModel(searchCityUseCase)

    private val statusObserver = mockk<Observer<ViewState>>()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel.status.observeForever(statusObserver)
    }

    @Test
    fun `searchCity onSuccess posts Success ViewState`() = runBlockingTest {
        // Configura el SearchCityUseCase para devolver una lista de ciudades
        val cities =
            listOf(CityUI(502209, "Bogota", "Cundinamarca", "CO", 4.6, -74.08, "America/Bogota"))
        coEvery { searchCityUseCase.invoke("Bogota") } returns Result.success(cities)

        // Llama a searchCity en el CityViewModel
        viewModel.searchCity("Bogota")

        // Verifica que se publicó un estado de éxito con la lista de ciudades
        assertEquals(ViewState.Success(cities), viewModel.status.value)
    }

    @Test
    fun `searchCity onFailure posts Error ViewState`() = runBlockingTest {
        // Configura el SearchCityUseCase para lanzar una excepción
        val exception = Exception("Error")
        coEvery { searchCityUseCase.invoke("Bogota") } returns Result.failure(exception)

        // Llama a searchCity en el CityViewModel
        viewModel.searchCity("Bogota")

        // Verifica que se publicó un estado de error con la excepción
        assertEquals(ViewState.Error(exception), viewModel.status.value)
    }

    @Test
    fun `searchCity with empty query does not post ViewState`() = runBlockingTest {
        // Llama a searchCity en el CityViewModel con una consulta vacía
        viewModel.searchCity("")

        // Verifica que no se publicó ningún estado
        assertNull(viewModel.status.value)
    }

    @After
    fun tearDown() {
        // Limpia el TestCoroutineDispatcher después de cada prueba
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
        viewModel.status.removeObserver(statusObserver)
    }
}