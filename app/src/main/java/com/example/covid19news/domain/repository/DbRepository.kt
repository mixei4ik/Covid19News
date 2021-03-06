package com.example.covid19news.domain.repository

import com.example.covid19news.domain.NewsModel
import kotlinx.coroutines.flow.Flow

interface DbRepository {

    suspend fun upsert(news: NewsModel): Long

    fun getSavedNews(): Flow<List<NewsModel>>

    suspend fun deleteEntityNews(news: NewsModel)
}
