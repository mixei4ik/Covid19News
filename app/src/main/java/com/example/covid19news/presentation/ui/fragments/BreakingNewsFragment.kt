package com.example.covid19news.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19news.R
import com.example.covid19news.databinding.FragmentBreakingNewsBinding
import com.example.covid19news.presentation.adapter.NewsAdapter
import com.example.covid19news.presentation.ui.MainActivity
import com.example.covid19news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private lateinit var binding: FragmentBreakingNewsBinding

    private lateinit var itemAdapter: NewsAdapter

    lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBreakingNewsBinding.bind(view)

        newsViewModel = (activity as MainActivity).newsViewModel

        setupRecycleView()

        itemAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("news", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_newsItemFragment,
                bundle
            )
        }

        lifecycleScope.launchWhenStarted {
            newsViewModel.country.collectLatest {
                newsViewModel.getBreakingNews(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            newsViewModel.items.collectLatest {
                if (it != null) {
                    itemAdapter.diff.submitList(it)
                    binding.progress.isVisible = false
                }
                else binding.progress.isVisible = true
            }
        }
    }

    private fun setupRecycleView() {
        itemAdapter = NewsAdapter()
        binding.recyclerViewBreakingNews.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}