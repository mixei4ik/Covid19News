package com.example.covid19news.presentation

import com.example.covid19news.domain.models.Statistic

data class StatisticState(
    val isLoading: Boolean = false,
    val statistic: Statistic? = null,
    val error: String = ""
)
