package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.common.Resource
import com.example.covid19news.domain.GetStatisticUseCase
import com.example.covid19news.presentation.StatisticState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
