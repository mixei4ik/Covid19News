package com.example.covid19news.domain.models

data class Statistic(
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int,
    val totalNewCases: Int,
    val totalNewDeaths: Int,
    val totalActiveCases: Int,
    val totalCasesPerMillionPop: Int,
    val created: String
)
