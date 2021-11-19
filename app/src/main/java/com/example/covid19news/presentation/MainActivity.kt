package com.example.covid19news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covid19news.R
import com.example.covid19news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}