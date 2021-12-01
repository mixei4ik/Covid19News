package com.example.covid19news.domain

import com.example.covid19news.domain.models.UserSettings
import com.example.covid19news.domain.repository.SettingsRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    fun execute(): UserSettings {
        return settingsRepository.getSettings()
    }
}
