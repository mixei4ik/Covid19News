package com.example.covid19news.data.repository

import com.example.covid19news.data.db.NewsEntity
import com.example.covid19news.data.db.NewsEntityDatabase
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.repository.DbRepository
import kotlinx.coroutines.flow.map

class DbRepositoryImpl(private val db: NewsEntityDatabase) : DbRepository {

    override suspend fun upsert(news: NewsModel) = db.getNewsEntityDao().upsert(mapFromDomainToStorage(news))

    override fun getSavedNews() = db.getNewsEntityDao().getAllEntityNews()
        .map { news -> news.map { mapFromStorageToDomain(it) }}

    override suspend fun deleteEntityNews(news: NewsModel) =
        db.getNewsEntityDao().deleteEntityNews(mapFromDomainToStorage(news))

    private fun mapFromDomainToStorage(saveNews: NewsModel): NewsEntity {
        return NewsEntity(
            saveNews.nid,
            saveNews.title,
            saveNews.description,
            saveNews.content,
            saveNews.url,
            saveNews.urlToImage,
            saveNews.publishedAt,
            saveNews.siteName
        )
    }

    private fun mapFromStorageToDomain(newsEntity: NewsEntity): NewsModel {
        return NewsModel(
            newsEntity.nid,
            newsEntity.title,
            newsEntity.description,
            newsEntity.content,
            newsEntity.url,
            newsEntity.urlToImage,
            newsEntity.publishedAt,
            newsEntity.siteName
        )
    }
}