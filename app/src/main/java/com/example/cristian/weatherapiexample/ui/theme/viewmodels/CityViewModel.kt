package com.example.cristian.weatherapiexample.ui.theme.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cristian.weatherapiexample.domain.useCases.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val searchCityUseCase: SearchCityUseCase) :
    ViewModel() {

    private val _status = MutableLiveData<ViewState>()
    val status: LiveData<ViewState> get() = _status

    fun searchCity(query: String) {
        viewModelScope.launch {
            if (!query.isNullOrEmpty()) {
                searchCityUseCase.invoke(query)
                    .onSuccess { cities ->
                        _status.postValue(ViewState.Success(cities))
                    }
                    .onFailure {
                        _status.postValue(ViewState.Error(it))
                    }
            }
        }
    }

    fun resetApiResponseStatus() {
        _status.value = null
    }
}
