package com.example.covid19news.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Inject


@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsEntityDatabase: RoomDatabase() {

    abstract fun getNewsEntityDao(): EntityNewsDao
}
