package com.cursoandroid.forecastmvvm.data.db.unitlocalized

import androidx.room.ColumnInfo
import androidx.room.TypeConverter
import com.cursoandroid.forecastmvvm.data.db.entity.unitlocalized.UnitSpecificCurrentWeatherEntry


data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "temperature")
    override val temperature: Double,
    //@ColumnInfo(name = "weatherDescriptions")
    //override val conditionText: List<String>,
    //@ColumnInfo(name = "weatherIcons")
    //override val conditionIcon: List<String>,
    @ColumnInfo(name = "windSpeed")
    override val windSpeed: Double,
    @ColumnInfo(name = "windDir")
    override val windDirection: String,
    @ColumnInfo(name = "precip")
    override val precipitationVolume: Double,
    @ColumnInfo(name = "feelslike")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name = "visibility")
    override val visibilityDistance: Double
) : UnitSpecificCurrentWeatherEntry
