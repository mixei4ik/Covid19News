package com.example.covid19news.data.model

import com.example.covid19news.domain.NewsModel

class NewsProvider {
    companion object {
        var news: List<NewsModel> = emptyList()
    }
}