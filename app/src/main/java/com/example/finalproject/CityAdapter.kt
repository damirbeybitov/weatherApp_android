package com.example.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ItemCityBinding

class CityAdapter(
    private val cities: List<City>,
    private val onCityClicked: (City) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city, onCityClicked)
    }

    override fun getItemCount(): Int = cities.size

    class CityViewHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City, onCityClicked: (City) -> Unit) {
            binding.textViewCityName.text = city.name
            itemView.setOnClickListener { onCityClicked(city) }
        }
    }
}
