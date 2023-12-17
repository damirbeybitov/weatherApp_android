package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.finalproject.databinding.FragmentCurrentWeatherBinding
import com.example.finalproject.network.WeatherService
import com.example.finalproject.model.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*
// You might need additional imports depending on your implementation

class CurrentWeatherFragment : Fragment() {

    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!

    private val cityName: String by lazy {
        CurrentWeatherFragmentArgs.fromBundle(requireArguments()).cityName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchCurrentWeather(cityName)
    }

    private fun fetchCurrentWeather(cityName: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getCurrentWeather(cityName)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let { weatherResponse ->
                        displayWeatherData(weatherResponse)
                    }
                } else {
                    handleError("Error: ${response.code()} ${response.message()}")
                }
            }
        }
    }
    private fun handleError(errorMessage: String) {
        // Display error message or log the error
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
    private fun displayWeatherData(weatherResponse: WeatherResponse) {
        // Update UI with weather data
        binding.textViewWeatherInfo.text = "City: ${weatherResponse.name}\n" +
                "Temperature: ${weatherResponse.main.temp}°C\n" +
                "Condition: ${weatherResponse.weather[0].main} - ${weatherResponse.weather[0].description}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
