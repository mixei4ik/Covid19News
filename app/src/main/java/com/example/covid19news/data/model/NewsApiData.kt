package com.example.covid19news.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsApiData(
    val total: Int,
    val items: List<News>
)

@JsonClass(generateAdapter = true)
data class News(
    val nid: Int?,
    val title: String?,
    val description: String?,
    val content: String?,
    val author: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val addedOn: String?,
    val siteName: String?,
    val language: String?,
    val countryCode: String?,
    val status: Int?
)