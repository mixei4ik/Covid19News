package com.example.covid19news.data.srorage

import com.example.covid19news.data.model.Settings
import kotlinx.coroutines.flow.Flow

interface UserStorage {

    fun saveSettings(settings: Settings) : Boolean

    fun getSettings() : Settings
}