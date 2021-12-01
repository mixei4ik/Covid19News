package com.example.covid19news.data.repository

import com.example.covid19news.data.model.Settings
import com.example.covid19news.data.srorage.UserStorage
import com.example.covid19news.domain.models.UserSettings
import com.example.covid19news.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val userStorage: UserStorage) : SettingsRepository {

    override fun saveSettings(userSettings: UserSettings) : Boolean {
        val setting = Settings(userSettings.darkThemeIncluded, userSettings.localization)
        return userStorage.saveSettings(setting)
    }

    override fun getSettings(): UserSettings {
        val settings = userStorage.getSettings()
        return UserSettings(settings.darkThemeIncluded, settings.localization)
    }
}