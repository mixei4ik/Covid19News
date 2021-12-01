package com.example.covid19news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsEntityDatabase : RoomDatabase() {

    abstract fun getNewsEntityDao(): EntityNewsDao
}
