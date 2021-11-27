package com.example.covid19news.domain.repository

import com.example.covid19news.domain.NewsModel

interface NewsRepository {

    suspend fun getAllNews(country: String): List<NewsModel>
}