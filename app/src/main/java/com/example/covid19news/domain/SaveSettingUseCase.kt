package com.example.covid19news.domain

import com.example.covid19news.domain.models.UserSettings
import com.example.covid19news.domain.repository.NewsRepository
import javax.inject.Inject

class SaveSettingUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    fun execute(userSettings: UserSettings) : Boolean {
        return repository.saveSettings(userSettings)
    }
}