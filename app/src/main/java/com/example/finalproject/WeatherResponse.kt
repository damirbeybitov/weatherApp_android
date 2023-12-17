package com.example.finalproject.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("name") val name: String,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind

) {
    data class Weather(
        @SerializedName("main") val main: String,
        @SerializedName("description") val description: String,

    )
    data class Main(
        @SerializedName("temp") val temp: Double,
        @SerializedName("feels_like") val feelsLike: Double,
        @SerializedName("temp_min") val tempMin: Double,
        @SerializedName("temp_max") val tempMax: Double,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("humidity") val humidity: Int

    )
    data class Wind(
        @SerializedName("speed") val speed: Double,
        @SerializedName("deg") val deg: Int,
        @SerializedName("gust") val gust: Double
    )
}
