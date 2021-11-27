package com.example.covid19news.di

import android.content.Context
import androidx.room.Room
import com.example.covid19news.data.db.EntityNewsDao
import com.example.covid19news.data.db.NewsEntityDatabase
import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.data.repository.NewsRepositoryImpl
import com.example.covid19news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideNewsRepository(db: NewsEntityDatabase, api: NewsService, newsProvider: NewsProvider): NewsRepository {
        return NewsRepositoryImpl(db, api, newsProvider)
    }

    @Singleton
    @Provides
    fun provideNewsEntityDatabase(@ApplicationContext context: Context): NewsEntityDatabase {
        return Room.databaseBuilder(
            context,
            NewsEntityDatabase::class.java,
            "news_db.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideEntityNewsDao(newsEntity: NewsEntityDatabase) : EntityNewsDao {
        return newsEntity.getNewsEntityDao()
    }
}
