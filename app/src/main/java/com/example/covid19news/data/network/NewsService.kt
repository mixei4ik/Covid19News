package com.example.covid19news.data.network


import com.example.covid19news.data.model.NewsApiData
import com.example.covid19news.data.model.StatisticApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsService @Inject constructor(
    private val api: NewsApiClient,
    private val apiStat: NewsApiStatisticClient
) {

    suspend fun getNews(country: String): NewsApiData {
        return withContext(Dispatchers.IO) {
            api.getListOfNews(country = country)
        }
    }

    suspend fun getStatistic(): StatisticApiData {
        return withContext(Dispatchers.IO) {
            apiStat.getStatistic()
        }
    }
}