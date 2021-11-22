package com.example.covid19news.data.repository

import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.repository.NewsRepository

class NewsRepositoryImpl: NewsRepository {

    private val api = NewsService()

    override suspend fun getAllNews(): List<NewsModel>{
        val response = api.getNews()
        NewsProvider.news = response
        return response
    }
}