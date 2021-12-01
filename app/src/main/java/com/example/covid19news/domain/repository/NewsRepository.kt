package com.example.covid19news.domain.repository

import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.models.Statistic
import com.example.covid19news.domain.models.UserSettings
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getAllNews(country: String): List<NewsModel>

    suspend fun upsert(news: NewsModel): Long

    suspend fun getStatistic(): Statistic

    fun getSavedNews(): Flow<List<NewsModel>>

    suspend fun deleteEntityNews(news: NewsModel)

    fun saveSettings(userSettings: UserSettings) : Boolean

    fun getSettings(): UserSettings
}