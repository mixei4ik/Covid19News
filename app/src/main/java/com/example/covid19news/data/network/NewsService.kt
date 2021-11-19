package com.example.covid19news.data.network

import com.example.covid19news.data.RetrofitHelper
import com.example.covid19news.domain.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsService {

    private val retrofit = RetrofitHelper.getRetrofit()

    val newsService = retrofit.create(NewsApiClient::class.java)

    suspend fun getNews(): List<NewsModel> {
        return withContext(Dispatchers.IO) {
            newsService.getListOfFilms()
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
        }
    }
}