package com.example.covid19news.domain

import com.example.covid19news.domain.repository.DbRepository
import javax.inject.Inject

class DeleteSavedNewsUseCase @Inject constructor(
    private val dbRepository: DbRepository
) {

    suspend fun deleteSavedNews(news: NewsModel) = dbRepository.deleteEntityNews(news)
}
