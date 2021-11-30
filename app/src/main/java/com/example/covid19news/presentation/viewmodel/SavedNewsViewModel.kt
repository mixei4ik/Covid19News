package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.domain.DeleteSavedNewsUseCase
import com.example.covid19news.domain.GetSavedNewsUseCase
import com.example.covid19news.domain.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
) : ViewModel() {

    fun getSavedNews() = getSavedNewsUseCase.getSavedNews()

    fun deleteSavedNews(news: NewsModel) = viewModelScope.launch {
        deleteSavedNewsUseCase.deleteSavedNews(news)
    }
}