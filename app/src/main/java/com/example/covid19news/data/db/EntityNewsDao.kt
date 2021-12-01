package com.example.covid19news.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EntityNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(news: NewsEntity): Long

    @Query("SELECT * FROM entity_news")
    fun getAllEntityNews(): Flow<List<NewsEntity>>

    @Delete
    suspend fun deleteEntityNews(news: NewsEntity)
}
