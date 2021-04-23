package com.cursoandroid.forecastmvvm.data.db.entity.unitlocalized

interface UnitSpecificCurrentWeatherEntry {

    val temperature: Double
   // val conditionText: List<String>
   // val conditionIcon: List<String>
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}