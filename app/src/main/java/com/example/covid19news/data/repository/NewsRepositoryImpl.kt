package com.example.covid19news.data.repository

import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl (
    private val api: NewsService,
    private val newsProvider: NewsProvider
): NewsRepository {

    override suspend fun getAllNews(): List<NewsModel>{
        val response = api.getNews()
        newsProvider.news = response
        return response
    }
}