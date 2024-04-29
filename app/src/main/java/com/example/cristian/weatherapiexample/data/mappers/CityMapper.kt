package com.example.cristian.weatherapiexample.data.mappers

import com.example.cristian.weatherapiexample.data.models.City
import com.example.cristian.weatherapiexample.domain.models.CityUI

fun City.toCityUI(): CityUI {
    return CityUI(
        id = id,
        name = name,
        region = region,
        country = country,
        lat = lat,
        lon = lon,
        url = url
    )
}
