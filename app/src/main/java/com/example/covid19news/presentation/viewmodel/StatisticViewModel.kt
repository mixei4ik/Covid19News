package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.common.Resource
import com.example.covid19news.domain.GetNewsUseCase
import com.example.covid19news.domain.GetSettingsUseCase
import com.example.covid19news.domain.GetStatisticUseCase
import com.example.covid19news.domain.SaveSettingUseCase
import com.example.covid19news.presentation.NewsListState
import com.example.covid19news.presentation.StatisticState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val getStatisticUseCase: GetStatisticUseCase
) : ViewModel() {

    private val _statisticState = MutableStateFlow<StatisticState>(StatisticState())
    val statisticState: StateFlow<StatisticState> = _statisticState.asStateFlow()

    init {
        getStatistic()
    }

    private fun getStatistic() {
        getStatisticUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _statisticState.value = StatisticState(statistic = result.data)
                }
                is Resource.Error -> {
                    _statisticState.value =
                        StatisticState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _statisticState.value = StatisticState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}