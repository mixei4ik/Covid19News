package com.example.covid19news.data.repository


import com.example.covid19news.data.db.NewsEntity
import com.example.covid19news.data.db.NewsEntityDatabase
import com.example.covid19news.data.model.News
import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.data.model.Settings
import com.example.covid19news.data.srorage.UserStorage
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.models.UserSettings
import com.example.covid19news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    private val db: NewsEntityDatabase,
    private val api: NewsService,
    private val newsProvider: NewsProvider,
    private val userStorage: UserStorage
) : NewsRepository {

    override suspend fun getAllNews(country: String): List<NewsModel> {
        val response = api.getNews(country)
            .items
            .map { mapFromApiDataToDomain(it) }
        newsProvider.news = response
        return response
    }

    override suspend fun upsert(news: NewsModel) = db.getNewsEntityDao().upsert(mapFromDomainToStorage(news))

    override fun getSavedNews() = db.getNewsEntityDao().getAllEntityNews()
        .map { news -> news.map { mapFromStorageToDomain(it) }}

    override suspend fun deleteEntityNews(news: NewsModel) =
        db.getNewsEntityDao().deleteEntityNews(mapFromDomainToStorage(news))

    override fun saveSettings(userSettings: UserSettings) : Boolean {
        val setting = Settings(userSettings.darkThemeIncluded, userSettings.localization)
        return userStorage.saveSettings(setting)
    }

    override fun getSettings(): UserSettings {
        val settings = userStorage.getSettings()
        return UserSettings(settings.darkThemeIncluded, settings.localization)
    }

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
}

