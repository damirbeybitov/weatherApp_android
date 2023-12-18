package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.adapter.ForecastAdapter
import com.example.finalproject.databinding.FragmentFiveDayForecastBinding
import com.example.finalproject.model.ForecastResponse
import com.example.finalproject.network.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*

class FiveDayForecastFragment : Fragment() {

    private var _binding: FragmentFiveDayForecastBinding? = null
    private val binding get() = _binding!!

    private val cityName: String by lazy {
        FiveDayForecastFragmentArgs.fromBundle(requireArguments()).cityName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFiveDayForecastBinding.inflate(inflater, container, false)
        fetchFiveDayForecast(cityName)
        return binding.root
    }

    private fun fetchFiveDayForecast(cityName: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                service.getFiveDayForecast(cityName)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Network error: ${e.message}", Toast.LENGTH_LONG).show()
                }
                return@launch
            }

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let { forecastResponse ->
                        displayForecastData(forecastResponse)
                    } ?: Toast.makeText(context, "Response was successful but the body was null", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Error: ${response.code()} ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // In FiveDayForecastFragment
    private fun displayForecastData(forecastResponse: ForecastResponse) {
        binding.textViewCity.text = cityName
        val adapter = ForecastAdapter(forecastResponse.list)
        binding.recyclerViewForecast.adapter = adapter
        binding.recyclerViewForecast.layoutManager = LinearLayoutManager(context)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
