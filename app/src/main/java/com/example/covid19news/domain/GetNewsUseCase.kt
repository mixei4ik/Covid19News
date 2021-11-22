package com.example.covid19news.domain

import com.example.covid19news.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {

    suspend operator fun invoke(): List<NewsModel> = repository.getAllNews()
}