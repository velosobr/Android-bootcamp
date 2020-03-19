package com.cursoandroid.forecastmvvm.data.response


import com.google.gson.annotations.SerializedName

data class Current(
    val cloudcover: Double,
    val feelslike: Double,
    val humidity: Double,
    @SerializedName("is_day")
    val isDay: String,
    @SerializedName("observation_time")
    val observationTime: String,
    val precip: Double,
    val pressure: Double,
    val temperature: Double,
    @SerializedName("uv_index")
    val uvIndex: Int,
    val visibility: Double,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>,
    @SerializedName("wind_degree")
    val windDegree: Int,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Int
)