package com.example.covid19news.domain

import com.example.covid19news.domain.models.NewsModel
import com.example.covid19news.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteSavedNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend fun deleteSavedNews(news: NewsModel) = repository.deleteEntityNews(news)
}