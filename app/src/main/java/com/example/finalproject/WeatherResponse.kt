package com.example.finalproject.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("name") val name: String
) {
    data class Weather(
        @SerializedName("main") val main: String,
        @SerializedName("description") val description: String
    )

    data class Main(
        @SerializedName("temp") val temp: Double
    )
}
