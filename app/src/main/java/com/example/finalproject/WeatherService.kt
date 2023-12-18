package com.example.finalproject.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.finalproject.model.ForecastResponse
import com.example.finalproject.model.WeatherResponse
// Additional imports may be necessary

interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = "b873cac991eb68bf19b06d18d407b701",
        @Query("units") units: String = "metric"
    ): Response<WeatherResponse> // Define WeatherResponse based on the API's JSON structure

    @GET("forecast")
    suspend fun getFiveDayForecast(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = "b873cac991eb68bf19b06d18d407b701",
        @Query("units") units: String = "metric"
    ): Response<ForecastResponse> // Define ForecastResponse based on the API's JSON structure
}
