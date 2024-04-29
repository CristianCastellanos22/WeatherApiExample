package com.example.cristian.weatherapiexample.domain.models

data class ForecastCityUI(
    val location: LocationUI?,
    val current: CurrentUI?,
    val forecast: ForecastUI?,
)

data class LocationUI(
    val name: String?,
    val region: String?,
    val country: String?,
    val lat: Double?,
    val lon: Double?,
    val tzId: String?,
    val localTime: String?,
)

data class CurrentUI(
    val tempC: Double?,
    val tempF: Double?,
    val condition: ConditionUI?,
    val windKph: Double?,
    val humidity: Int?,
    val cloud: Int?,
)

data class ConditionUI(
    val text: String?,
    val icon: String?,
    val code: Int?,
)

data class ForecastUI(
    val forecastDay: List<ForeseeUI>?,
)

data class ForeseeUI(
    val date: String?,
    val day: DayUI?,
    val hour: List<HourUI>?,
)

data class DayUI(
    val maxtempC: Double?,
    val mintempC: Double?,
    val avgtempC: Double?,
    val maxwindKph: Double?,
    val condition: ConditionUI?,
)

data class HourUI(
    val time: String?,
    val tempC: Double?,
    val condition: ConditionUI?,
    val windKph: Double?,
    val humidity: Int?,
    val cloud: Int?,
    val chanceOfRain: String?,
)
