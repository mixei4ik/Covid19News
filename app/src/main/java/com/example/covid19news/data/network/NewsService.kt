package com.example.covid19news.data.network


import com.example.covid19news.data.model.NewsApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsService @Inject constructor(
    private val api: NewsApiClient
) {

    suspend fun getNews(): NewsApiData {
        return withContext(Dispatchers.IO) {
            api.getListOfNews()
        }
    }
}