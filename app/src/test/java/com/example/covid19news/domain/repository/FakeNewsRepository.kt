package com.example.covid19news.domain.repository

import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.models.Statistic

class FakeNewsRepository : NewsRepository {

    private val newsItems = listOf<NewsModel>(NewsModel(1, "", "", "", "", "", "", ""))

    private val stat = Statistic(1, 1, 1, 1, 1, 1, 1, "")

    override suspend fun getAllNews(country: String): List<NewsModel> {
        return newsItems
    }

    override suspend fun getStatistic(): Statistic {
        return stat
    }
}
