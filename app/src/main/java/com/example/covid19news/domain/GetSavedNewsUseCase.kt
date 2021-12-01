package com.example.covid19news.domain

import com.example.covid19news.domain.repository.DbRepository
import javax.inject.Inject

class GetSavedNewsUseCase @Inject constructor(
    private val dbRepository: DbRepository
) {

    fun getSavedNews() = dbRepository.getSavedNews()
}
