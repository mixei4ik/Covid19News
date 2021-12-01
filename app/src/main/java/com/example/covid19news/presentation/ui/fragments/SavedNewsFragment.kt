package com.example.covid19news.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19news.R
import com.example.covid19news.databinding.FragmentSavedNewsBinding
import com.example.covid19news.presentation.adapter.NewsAdapter
import com.example.covid19news.presentation.ui.MainActivity
import com.example.covid19news.presentation.viewmodel.SavedNewsViewModel
import kotlinx.coroutines.flow.collectLatest

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private lateinit var binding: FragmentSavedNewsBinding

    private lateinit var itemAdapter: NewsAdapter

    lateinit var savedNewsViewModel: SavedNewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
        savedNewsViewModel = (activity as MainActivity).savedNewsViewModel

        setupRecycleView()

        itemAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("news", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_newsItemFragment,
                bundle
            )
        }

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val news = itemAdapter.diff.currentList[position]
                savedNewsViewModel.deleteSavedNews(news)
                Toast.makeText(context, "Deleted news", Toast.LENGTH_SHORT).show()
            }

        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.recyclerViewSavedNews)
        }

        lifecycleScope.launchWhenStarted {
            savedNewsViewModel.savedNews.collectLatest {
                itemAdapter.diff.submitList(it)
            }
        }
    }

    private fun setupRecycleView() {
        itemAdapter = NewsAdapter()
        binding.recyclerViewSavedNews.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}