package com.example.covid19news.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "entity_news")
data class NewsEntity(
    @PrimaryKey val nid: Int,
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
)
