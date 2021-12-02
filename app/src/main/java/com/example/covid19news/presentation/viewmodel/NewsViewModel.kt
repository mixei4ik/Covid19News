package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.common.Constants
import com.example.covid19news.common.Resource
import com.example.covid19news.domain.GetNewsUseCase
import com.example.covid19news.domain.GetSettingsUseCase
import com.example.covid19news.domain.SaveSettingUseCase
import com.example.covid19news.domain.models.UserSettings
import com.example.covid19news.presentation.NewsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingUseCase: SaveSettingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _itemsState = MutableStateFlow<NewsListState>(NewsListState())
    val itemsState: StateFlow<NewsListState> = _itemsState.asStateFlow()

    private val _darkThemeOn = MutableStateFlow<Boolean>(false)
    val darkThemeOn: StateFlow<Boolean> = _darkThemeOn.asStateFlow()

    init {
        loadSettings()
        savedStateHandle.get<String>(Constants.PARAM_COUNTRY)?. let { countryInit ->
            getBreakingNews(countryInit)
        }
    }

    private fun getBreakingNews(countryInit: String) {
        getNewsUseCase(countryInit).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _itemsState.value = NewsListState(news = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _itemsState.value = NewsListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _itemsState.value = NewsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveSetting(darkThemeIncluded: Boolean, localization: String) {
        val param = UserSettings(darkThemeIncluded, localization)
        saveSettingUseCase.execute(param)
    }

    fun loadSettings() {
        val settings = getSettingsUseCase.execute()
        _darkThemeOn.value = settings.darkThemeIncluded
        getBreakingNews(settings.localization)
    }
}
