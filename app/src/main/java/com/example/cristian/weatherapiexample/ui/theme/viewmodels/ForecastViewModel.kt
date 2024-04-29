package com.example.cristian.weatherapiexample.ui.theme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cristian.weatherapiexample.domain.useCases.SearchForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val searchForecastUseCase: SearchForecastUseCase) :
    ViewModel() {

    private val _status = MutableLiveData<ViewState>(ViewState.Loading)
    val status: LiveData<ViewState> get() = _status
        fun searchForecast(query: String) {
            viewModelScope.launch {
                searchForecastUseCase.invoke(query)
                    .onSuccess { _status.postValue(ViewState.Success(it)) }
                    .onFailure { _status.postValue(ViewState.Error(it)) }
            }
        }
}
