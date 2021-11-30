package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.domain.*
import com.example.covid19news.domain.models.UserSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingUseCase: SaveSettingUseCase
): ViewModel() {

    private val _items = MutableStateFlow<List<NewsModel>?>(null)
    val items: StateFlow<List<NewsModel>?> = _items.asStateFlow()

    val savedItems: StateFlow<List<NewsModel>> = getSavedNewsUseCase.getSavedNews().stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    var darkThemeIncluded: Boolean = false

    private val _country = MutableStateFlow<String>("")
    val country: StateFlow<String> = _country.asStateFlow()

    private var countryInit = ""

    init {
        loadSettings()
        getBreakingNews(countryInit)
    }

    fun getBreakingNews(country: String) = viewModelScope.launch {
            val result = getNewsUseCase.invoke(country)
            _items.value = result
        }

    fun saveNews(news: NewsModel) = viewModelScope.launch {
        saveNewsUseCase.saveNews(news)
    }

    fun deleteSavedNews(news: NewsModel) = viewModelScope.launch {
        deleteSavedNewsUseCase.deleteSavedNews(news)
    }

    fun saveSetting(darkThemeIncluded: Boolean, localization: String) {
        val param = UserSettings(darkThemeIncluded, localization)
        saveSettingUseCase.execute(param)
    }

    fun loadSettings() {
        val settings = getSettingsUseCase.execute()
        _country.value = settings.localization
        countryInit = settings.localization
        darkThemeIncluded = settings.darkThemeIncluded
    }
}