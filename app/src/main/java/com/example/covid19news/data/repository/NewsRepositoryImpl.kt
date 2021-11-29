package com.example.covid19news.data.repository


import com.example.covid19news.data.db.NewsEntity
import com.example.covid19news.data.db.NewsEntityDatabase
import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val db: NewsEntityDatabase,
    private val api: NewsService,
    private val newsProvider: NewsProvider
) : NewsRepository {

    override suspend fun getAllNews(country: String): List<NewsModel> {
        val response = api.getNews(country)
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

    override suspend fun upsert(news: NewsModel) = db.getNewsEntityDao().upsert(mapToStorage(news))

    override fun getSavedNews() = db.getNewsEntityDao().getAllEntityNews()
        .map { news -> news.map { mapToDomain(it) }}
/*        .map { news ->
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
        }*/

    override suspend fun deleteEntityNews(news: NewsModel) =
        db.getNewsEntityDao().deleteEntityNews(mapToStorage(news))

    private fun mapToStorage(saveNews: NewsModel): NewsEntity {
        return NewsEntity(
            saveNews.nid,
            saveNews.title,
            saveNews.description,
            saveNews.content,
            saveNews.author,
            saveNews.url,
            saveNews.urlToImage,
            saveNews.publishedAt,
            saveNews.addedOn,
            saveNews.siteName,
            saveNews.language,
            saveNews.countryCode,
            saveNews.status
        )
    }

    private fun mapToDomain(newsEntity: NewsEntity): NewsModel {
        return NewsModel(
            newsEntity.nid,
            newsEntity.title,
            newsEntity.description,
            newsEntity.content,
            newsEntity.author,
            newsEntity.url,
            newsEntity.urlToImage,
            newsEntity.publishedAt,
            newsEntity.addedOn,
            newsEntity.siteName,
            newsEntity.language,
            newsEntity.countryCode,
            newsEntity.status
        )
    }
}

