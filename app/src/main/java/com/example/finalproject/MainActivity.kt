package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.finalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Optionally setup a custom toolbar as the ActionBar
//        setSupportActionBar(binding.toolbar) // Replace 'toolbar' with your toolbar's ID if you're using a custom toolbar.

        // Setting up Navigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Set up ActionBar with NavController
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() ||
                (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                        as NavHostFragment).navController.navigateUp()
    }
}
