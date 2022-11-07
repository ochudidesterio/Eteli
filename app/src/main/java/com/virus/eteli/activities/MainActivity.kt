package com.virus.eteli.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.virus.eteli.R
import com.virus.eteli.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val navController =Navigation.findNavController(this@MainActivity,R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(bottomNav,navController)
    }
}