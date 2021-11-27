package com.example.covid19news.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Inject


@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsEntityDatabase: RoomDatabase() {

    abstract fun getNewsEntityDao(): EntityNewsDao

/*    companion object {
        @Volatile
        private var instance: NewsEntityDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsEntityDatabase::class.java,
                "news_db.db"
            ).build()
    }*/
}
