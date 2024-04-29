package com.example.cristian.weatherapiexample.data.models

import com.google.gson.annotations.SerializedName

data class ForecastCity(
    @SerializedName("location")
    val location: Location?,
    @SerializedName("current")
    val current: Current?,
    @SerializedName("forecast")
    val forecast: Forecast?,
)

data class Location(
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("tz_id")
    val tzId: String?,
    @SerializedName("localtime")
    val localTime: String?,
)

data class Current(
    @SerializedName("temp_c")
    val tempC: Double?,
    @SerializedName("temp_f")
    val tempF: Double?,
    @SerializedName("condition")
    val condition: Condition?,
    @SerializedName("wind_kph")
    val windKph: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("cloud")
    val cloud: Int?,
)

data class Condition(
    val text: String?,
    val icon: String?,
    val code: Int?,
)

data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<Foresee>?,
)

data class Foresee(
    val date: String?,
    val day: Day?,
    val hour: List<Hour>?,
)

data class Day(
    @SerializedName("maxtemp_c")
    val maxtempC: Double?,
    @SerializedName("mintemp_c")
    val mintempC: Double?,
    @SerializedName("avgtemp_c")
    val avgtempC: Double?,
    @SerializedName("maxwind_kph")
    val maxwindKph: Double?,
    @SerializedName("condition")
    val condition: Condition?,
)

data class Hour(
    @SerializedName("time")
    val time: String?,
    @SerializedName("temp_c")
    val tempC: Double?,
    @SerializedName("condition")
    val condition: Condition?,
    @SerializedName("wind_kph")
    val windKph: Double?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("cloud")
    val cloud: Int?,
    @SerializedName("chance_of_rain")
    val chanceOfRain: String?,
)
