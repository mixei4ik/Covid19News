package com.example.covid19news.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatisticApiData(
    val totalConfirmed: Int?,
    val totalDeaths: Int?,
    val totalRecovered: Int?,
    val totalNewCases: Int?,
    val totalNewDeaths: Int?,
    val totalActiveCases: Int?,
    val totalCasesPerMillionPop: Int?,
    val created: String?
)
