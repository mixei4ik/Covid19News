package com.example.covid19news.domain.repository

import com.example.covid19news.domain.models.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getAllNews(country: String): List<NewsModel>

    suspend fun upsert(news: NewsModel): Long

    fun getSavedNews(): Flow<List<NewsModel>>

    suspend fun deleteEntityNews(news: NewsModel)
}