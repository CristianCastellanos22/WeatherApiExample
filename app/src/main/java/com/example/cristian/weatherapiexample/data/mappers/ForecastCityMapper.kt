package com.example.cristian.weatherapiexample.data.mappers

import com.example.cristian.weatherapiexample.data.models.Condition
import com.example.cristian.weatherapiexample.data.models.Current
import com.example.cristian.weatherapiexample.data.models.Day
import com.example.cristian.weatherapiexample.data.models.Forecast
import com.example.cristian.weatherapiexample.data.models.ForecastCity
import com.example.cristian.weatherapiexample.data.models.Foresee
import com.example.cristian.weatherapiexample.data.models.Hour
import com.example.cristian.weatherapiexample.data.models.Location
import com.example.cristian.weatherapiexample.domain.models.ConditionUI
import com.example.cristian.weatherapiexample.domain.models.CurrentUI
import com.example.cristian.weatherapiexample.domain.models.DayUI
import com.example.cristian.weatherapiexample.domain.models.ForecastCityUI
import com.example.cristian.weatherapiexample.domain.models.ForecastUI
import com.example.cristian.weatherapiexample.domain.models.ForeseeUI
import com.example.cristian.weatherapiexample.domain.models.HourUI
import com.example.cristian.weatherapiexample.domain.models.LocationUI

fun ForecastCity.toForecastCityUI(): ForecastCityUI {
    return ForecastCityUI(
        location = location?.toLocationUI(),
        current = current?.toCurrentUI(),
        forecast = forecast?.toForecastUI()
    )
}

fun Location.toLocationUI(): LocationUI{
    return LocationUI(
        name = name,
        region = region,
        country = country,
        lat = lat,
        lon = lon,
        tzId = tzId,
        localTime = localTime
    )
}

fun Current.toCurrentUI(): CurrentUI {
    return CurrentUI(
        tempC = tempC,
        tempF = tempF,
        condition = condition?.toConditionUI(),
        windKph = windKph,
        humidity = humidity,
        cloud = cloud
    )
}

fun Condition.toConditionUI(): ConditionUI {
    return ConditionUI(
        text = text,
        icon = icon,
        code = code
    )
}

fun Forecast.toForecastUI(): ForecastUI {
    return ForecastUI(
        forecastDay = forecastDay?.map { it.toForeseeUI() }
    )
}

fun Foresee.toForeseeUI(): ForeseeUI {
    return ForeseeUI(
        date = date,
        day = day?.toDayUI(),
        hour = hour?.map { it.toHourUI() }
    )
}

fun Day.toDayUI(): DayUI {
    return DayUI(
        maxtempC = maxtempC,
        mintempC = mintempC,
        avgtempC = avgtempC,
        maxwindKph = maxwindKph,
        condition = condition?.toConditionUI()
    )
}

fun Hour.toHourUI(): HourUI {
    return HourUI(
        time = time,
        tempC = tempC,
        condition = condition?.toConditionUI(),
        windKph = windKph,
        humidity = humidity,
        cloud = cloud,
        chanceOfRain = chanceOfRain
    )
}
