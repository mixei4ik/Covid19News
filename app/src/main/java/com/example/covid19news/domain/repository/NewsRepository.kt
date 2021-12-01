package com.example.covid19news.domain.repository

import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.models.Statistic

interface NewsRepository {

    suspend fun getAllNews(country: String): List<NewsModel>

    suspend fun getStatistic(): Statistic
}
