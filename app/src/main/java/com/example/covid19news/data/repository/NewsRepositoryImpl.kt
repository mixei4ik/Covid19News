package com.example.covid19news.data.repository

import com.example.covid19news.data.model.News
import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.model.StatisticApiData
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.models.Statistic
import com.example.covid19news.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val api: NewsService,
    private val newsProvider: NewsProvider
) : NewsRepository {

    override suspend fun getAllNews(country: String): List<NewsModel> {
        val response = api.getNews(country)
            .items
            .map { mapFromApiDataToDomain(it) }
        newsProvider.news = response
        return response
    }

    override suspend fun getStatistic(): Statistic {
        return mapStatisticToDomain(api.getStatistic())
    }

    private fun mapFromApiDataToDomain(newsApiData: News): NewsModel {
        return NewsModel(
            newsApiData.nid ?: 1,
            newsApiData.title ?: "no data",
            newsApiData.description ?: "no data",
            newsApiData.content ?: "no data",
            newsApiData.url ?: "no data",
            newsApiData.urlToImage ?: "no data",
            newsApiData.publishedAt ?: "no data",
            newsApiData.siteName ?: "no data"
        )
    }

    private fun mapStatisticToDomain(statisticApi: StatisticApiData): Statistic {
        return Statistic(
            statisticApi.totalConfirmed ?: 0,
            statisticApi.totalDeaths ?: 0,
            statisticApi.totalRecovered ?: 0,
            statisticApi.totalNewCases ?: 0,
            statisticApi.totalNewDeaths ?: 0,
            statisticApi.totalActiveCases ?: 0,
            statisticApi.totalCasesPerMillionPop ?: 0,
            statisticApi.created ?: "no data"
        )
    }
}
