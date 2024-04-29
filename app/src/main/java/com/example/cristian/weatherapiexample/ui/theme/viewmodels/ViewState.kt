package com.example.cristian.weatherapiexample.ui.theme.viewmodels

sealed class ViewState {
    object Loading : ViewState()
    data class Success<T>(val data: T) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}
