package com.example.finalproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemForecastBinding
import com.example.finalproject.model.ForecastResponse
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ForecastAdapter(private val forecastList: List<ForecastResponse.Forecast>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(private val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: ForecastResponse.Forecast) {
            val originalFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val date = originalFormat.parse(forecast.dtTxt)

            date?.let {
                val calendar = Calendar.getInstance()
                calendar.time = date
                val startDate = originalFormat.format(calendar.time)

                calendar.add(Calendar.HOUR_OF_DAY, 3)
                val endDate = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(calendar.time)

                binding.textViewDateTime.text = "Date/Time: $startDate - $endDate"

            }
            binding.textViewTemp.text = "Temperature: ${String.format("%.1f", forecast.main.temp)}°C"
            binding.textViewFeelsLike.text = "Feels Like: ${String.format("%.1f", forecast.main.feelsLike)}°C"
            binding.textViewTempMin.text = "Min Temp: ${String.format("%.1f", forecast.main.tempMin)}°C"
            binding.textViewTempMax.text = "Max Temp: ${String.format("%.1f", forecast.main.tempMax)}°C"
            binding.textViewPressure.text = "Pressure: ${forecast.main.pressure} hPa"
            binding.textViewHumidity.text = "Humidity: ${forecast.main.humidity}%"
            binding.textViewCondition.text = "Condition: ${forecast.weather[0].main} (${forecast.weather[0].description})"
            binding.textViewVisibility.text = "Visibility: ${(forecast.visibility / 1000.0).format(1)} km"
            binding.textViewWindSpeed.text = "Wind Speed: ${String.format("%.1f", forecast.wind.speed)} m/s"
            binding.textViewWindDirection.text = "Wind Direction: ${forecast.wind.deg}°"
            binding.textViewWindGust.text = "Wind Gust: ${String.format("%.1f", forecast.wind.gust)} m/s"
        }

        private fun Double.format(digits: Int) = "%.${digits}f".format(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size
}
