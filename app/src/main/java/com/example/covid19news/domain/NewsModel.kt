package com.example.covid19news.domain

import java.io.Serializable

data class NewsModel(
    val nid: Int,
    val title: String,
    val description: String,
    val content: String,
    val author: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val addedOn: String,
    val siteName: String,
    val language: String,
    val countryCode: String,
    val status: Int
) : Serializable