package com.example.covid19news.domain

import com.example.covid19news.domain.models.NewsModel
import com.example.covid19news.domain.repository.NewsRepository
import javax.inject.Inject

class SaveNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend fun saveNews(news: NewsModel) = repository.upsert(news)
}