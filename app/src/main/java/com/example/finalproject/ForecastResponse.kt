package com.example.finalproject.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("list") val list: List<Forecast>
) {
    data class Forecast(
        @SerializedName("dt") val dt: Long,
        @SerializedName("main") val main: Main,
        @SerializedName("weather") val weather: List<Weather>,
        @SerializedName("wind") val wind: Wind,
        @SerializedName("visibility") val visibility: Int,
        @SerializedName("dt_txt") val dtTxt: String
        // Add other fields as necessary
    )

    data class Main(
        @SerializedName("temp") val temp: Double,
        @SerializedName("feels_like") val feelsLike: Double,
        @SerializedName("temp_min") val tempMin: Double,
        @SerializedName("temp_max") val tempMax: Double,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("humidity") val humidity: Int
    )

    data class Weather(
        @SerializedName("main") val main: String,
        @SerializedName("description") val description: String
    )

    data class Wind(
        @SerializedName("speed") val speed: Double,
        @SerializedName("deg") val deg: Int,
        @SerializedName("gust") val gust: Double
    )
}
