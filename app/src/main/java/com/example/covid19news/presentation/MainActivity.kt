package com.example.covid19news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19news.databinding.ActivityMainBinding
import com.example.covid19news.presentation.adapter.NewsAdapter
import com.example.covid19news.presentation.viewmodel.NewsViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val itemAdapter = NewsAdapter()

    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsViewModel.onCreate()

        binding.recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        newsViewModel.items.observe(this, Observer {
            it ?: return@Observer
            itemAdapter.addItems(it)
        })
    }
}