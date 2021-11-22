package com.example.covid19news.data.network


import com.example.covid19news.domain.NewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsService @Inject constructor(
    private val api: NewsApiClient
) {

    suspend fun getNews(): List<NewsModel> {
        return withContext(Dispatchers.IO) {
            api.getListOfFilms()
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