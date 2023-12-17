package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finalproject.databinding.FragmentCityDetailsBinding

class CityDetailsFragment : Fragment() {

    private var _binding: FragmentCityDetailsBinding? = null
    private val binding get() = _binding!!

    // Retrieve the city name passed from CityListFragment
    private val city: City by lazy {
        CityDetailsFragmentArgs.fromBundle(requireArguments()).city
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.hide()
        binding.textViewCityName.text = city.name
        binding.textViewCityDescription.text = city.description
        // Setup buttons for weather now and 5-day forecast
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
