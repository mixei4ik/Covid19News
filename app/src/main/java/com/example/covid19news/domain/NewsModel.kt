package com.example.covid19news.domain

import java.io.Serializable

data class NewsModel(
    val nid: Int,
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val siteName: String
) : Serializable
