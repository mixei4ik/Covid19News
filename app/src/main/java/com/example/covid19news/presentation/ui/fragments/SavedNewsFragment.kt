package com.example.covid19news.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19news.R
import com.example.covid19news.databinding.FragmentBreakingNewsBinding
import com.example.covid19news.databinding.FragmentSavedNewsBinding
import com.example.covid19news.presentation.adapter.NewsAdapter
import com.example.covid19news.presentation.ui.MainActivity
import com.example.covid19news.presentation.viewmodel.NewsViewModel

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private lateinit var binding: FragmentSavedNewsBinding

    private val itemAdapter = NewsAdapter()

    lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
        newsViewModel = (activity as MainActivity).newsViewModel

        binding.recyclerViewSavedNews.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        itemAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("news", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_newsItemFragment,
                bundle
            )
        }
    }
}