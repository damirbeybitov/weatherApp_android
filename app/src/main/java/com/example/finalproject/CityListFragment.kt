package com.example.finalproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.FragmentCityListBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cities = parseCitiesCSV(requireContext())
        setupRecyclerView(cities)
    }

    private fun setupRecyclerView(cities: List<City>) {
        val adapter = CityAdapter(cities) { city ->
            val action = CityListFragmentDirections.actionCityListFragmentToCityDetailsFragment(city)
            findNavController().navigate(action)
        }
        binding.recyclerViewCities.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCities.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun parseCitiesCSV(context: Context): List<City> {
        val cities = mutableListOf<City>()
        try {
            val inputStream = context.assets.open("cities_weather.csv")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                val tokens = line!!.split(",")
                if (tokens.isNotEmpty()) {
                    val city = City(name = tokens[0], description = tokens[1])
                    cities.add(city)
                }
            }
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return cities
    }
}
