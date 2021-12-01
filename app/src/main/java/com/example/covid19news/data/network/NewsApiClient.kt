package com.example.covid19news.data.network

import com.example.covid19news.data.model.NewsApiData
import com.example.covid19news.data.model.StatisticApiData
import retrofit2.http.GET
import retrofit2.http.Query

private const val LIMIT = 20

interface NewsApiClient {
    @GET("/news/trending?&offset")
    suspend fun getListOfNews(
        @Query("limit") limit: Int = LIMIT,
        @Query("country") country: String = ""
    ): NewsApiData
}

interface NewsApiStatisticClient {
    @GET("/v3/stats/worldometer/global")
    suspend fun getStatistic(): StatisticApiData
}
