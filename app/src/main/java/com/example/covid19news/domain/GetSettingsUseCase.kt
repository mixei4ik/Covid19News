package com.example.covid19news.domain

import com.example.covid19news.domain.models.UserSettings
import com.example.covid19news.domain.repository.NewsRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    fun execute(): UserSettings {
        return repository.getSettings()
    }
}