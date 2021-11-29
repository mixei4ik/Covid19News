package com.example.covid19news.domain

import com.example.covid19news.domain.repository.NewsRepository
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    fun getSavedNews() = repository.getSavedNews()
}