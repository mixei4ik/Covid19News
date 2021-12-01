package com.example.covid19news.domain.repository

import com.example.covid19news.domain.models.UserSettings

interface SettingsRepository {

    fun saveSettings(userSettings: UserSettings) : Boolean

    fun getSettings(): UserSettings
}