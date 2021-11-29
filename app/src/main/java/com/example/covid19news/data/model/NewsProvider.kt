package com.example.covid19news.data.model

import com.example.covid19news.domain.NewsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsProvider @Inject constructor() {

        var news: List<NewsModel> = emptyList()
}