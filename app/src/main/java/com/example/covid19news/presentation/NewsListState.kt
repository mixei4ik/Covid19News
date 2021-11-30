package com.example.covid19news.presentation

import com.example.covid19news.domain.NewsModel

data class NewsListState(
    val isLoading: Boolean = false,
    val news: List<NewsModel> = emptyList(),
    val error: String = ""
)
