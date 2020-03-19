package com.cursoandroid.forecastmvvm.data.network.response


import com.cursoandroid.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.cursoandroid.forecastmvvm.data.db.entity.Location
import com.cursoandroid.forecastmvvm.data.db.entity.Request

data class CurrentWeatherResponse(
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)