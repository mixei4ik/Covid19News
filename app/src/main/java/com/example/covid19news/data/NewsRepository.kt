package com.example.covid19news.data

import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.domain.NewsModel

class NewsRepository {

    private val api = NewsService()

    suspend fun getAllNews(): List<NewsModel>{
        val response = api.getNews()
        NewsProvider.news = response
        return response
    }
}