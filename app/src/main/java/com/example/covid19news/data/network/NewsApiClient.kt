package com.example.covid19news.data.network

import com.example.covid19news.data.model.NewsApiData
import retrofit2.http.GET

interface NewsApiClient {
    @GET("/news/trending?limit=20&offset")
    suspend fun getListOfFilms(): NewsApiData
}