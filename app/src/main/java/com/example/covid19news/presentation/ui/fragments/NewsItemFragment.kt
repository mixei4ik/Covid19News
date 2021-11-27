package com.example.covid19news.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.covid19news.R
import com.example.covid19news.databinding.FragmentNewsItemBinding
import com.example.covid19news.databinding.FragmentSavedNewsBinding
import com.example.covid19news.presentation.ui.MainActivity
import com.example.covid19news.presentation.viewmodel.NewsViewModel

class NewsItemFragment: Fragment(R.layout.fragment_news_item) {

    private lateinit var binding: FragmentNewsItemBinding

    lateinit var newsViewModel: NewsViewModel

    private val args: NewsItemFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsItemBinding.bind(view)
        newsViewModel = (activity as MainActivity).newsViewModel

        binding.contentItemView.text = args.news.content
        binding.descriptionItemView.text = args.news.description
        binding.siteNameItemView.text = args.news.siteName
        binding.timeItemView.text = args.news.publishedAt
        binding.titleItemView.text = args.news.title
        binding.urlNameItemView.text = args.news.url
        binding.imageItemView.load(args.news.urlToImage){
            placeholder(R.drawable.ic_image)
            error(R.drawable.ic_image)
            crossfade(true)
        }
    }
}