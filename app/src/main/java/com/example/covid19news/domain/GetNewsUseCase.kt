package com.example.covid19news.domain

import com.example.covid19news.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(): List<NewsModel> = repository.getAllNews()
}