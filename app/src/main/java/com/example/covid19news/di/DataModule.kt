package com.example.covid19news.di

import com.example.covid19news.data.model.NewsProvider
import com.example.covid19news.data.network.NewsService
import com.example.covid19news.data.repository.NewsRepositoryImpl
import com.example.covid19news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideNewsRepository(api: NewsService, newsProvider: NewsProvider): NewsRepository {
        return NewsRepositoryImpl(api, newsProvider)
    }
}
