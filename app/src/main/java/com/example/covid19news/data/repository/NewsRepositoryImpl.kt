package com.example.covid19news.data.repository

import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val api: NewsService,
    private val newsProvider: NewsProvider
) : NewsRepository {

    override suspend fun getAllNews(): List<NewsModel> {
        val response = api.getNews()
            .items
            .map { news ->
                NewsModel(
                    news.nid,
                    news.title,
                    news.description,
                    news.content,
                    news.author,
                    news.url,
                    news.urlToImage,
                    news.publishedAt,
                    news.addedOn,
                    news.siteName,
                    news.language,
                    news.countryCode,
                    news.status
                )
            }

        newsProvider.news = response
        return response
    }
}