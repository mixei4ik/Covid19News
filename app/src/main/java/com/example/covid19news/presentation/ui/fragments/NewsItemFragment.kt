package com.example.covid19news.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.covid19news.R
import com.example.covid19news.databinding.FragmentNewsItemBinding
import com.example.covid19news.domain.SaveNewsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsItemFragment : Fragment(R.layout.fragment_news_item) {

    private lateinit var binding: FragmentNewsItemBinding

    @Inject
    lateinit var saveNewsUseCase: SaveNewsUseCase

    private val args: NewsItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsItemBinding.bind(view)
        val news = args.news

        binding.contentItemView.text = news.content
        binding.descriptionItemView.text = news.description
        binding.siteNameItemView.text = news.siteName
        binding.timeItemView.text = news.publishedAt
        binding.titleItemView.text = news.title
        binding.urlNameItemView.text = news.url
        binding.imageItemView.load(news.urlToImage) {
            placeholder(R.drawable.ic_image)
            error(R.drawable.ic_image)
            crossfade(true)
        }
        binding.saveNewsButton.setOnClickListener {
            lifecycleScope.launch {
                saveNewsUseCase.saveNews(news)
            }
            Toast.makeText(context, "News saved", Toast.LENGTH_SHORT).show()
        }
    }
}
