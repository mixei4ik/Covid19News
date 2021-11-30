package com.example.covid19news.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "entity_news")
data class NewsEntity(
    @PrimaryKey val nid: Int,
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val siteName: String
)
