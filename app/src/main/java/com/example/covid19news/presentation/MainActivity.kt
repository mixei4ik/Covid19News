package com.example.covid19news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19news.databinding.ActivityMainBinding
import com.example.covid19news.presentation.adapter.NewsAdapter
import com.example.covid19news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val itemAdapter = NewsAdapter()

    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launchWhenStarted {
            newsViewModel.items.collectLatest {
                if (it != null) {
                    itemAdapter.addItems(it)
                    binding.progress.isVisible = false
                }
                else binding.progress.isVisible = true
            }
        }
    }
}