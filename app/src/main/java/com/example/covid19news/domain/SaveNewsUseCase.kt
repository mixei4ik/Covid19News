package com.example.covid19news.domain

import com.example.covid19news.domain.repository.DbRepository
import com.example.covid19news.domain.repository.NewsRepository
import javax.inject.Inject

class SaveNewsUseCase @Inject constructor(
    private val dbRepository: DbRepository
) {

    suspend fun saveNews(news: NewsModel) = dbRepository.upsert(news)
}