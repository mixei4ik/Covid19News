package com.example.covid19news.di

import com.example.covid19news.data.RetrofitHelper
import com.example.covid19news.data.network.NewsApiClient
import com.example.covid19news.data.network.NewsApiStatisticClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return RetrofitHelper.getRetrofit()
    }

    @Singleton
    @Provides
    fun provideNewsApiClient(retrofit: Retrofit): NewsApiClient{
        return retrofit.create(NewsApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsApiStatisticClient(retrofit: Retrofit): NewsApiStatisticClient {
        return retrofit.create(NewsApiStatisticClient::class.java)
    }
}