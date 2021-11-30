package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.SaveNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val saveNewsUseCase: SaveNewsUseCase,
) : ViewModel() {

    fun saveNews(news: NewsModel) = viewModelScope.launch {
        saveNewsUseCase.saveNews(news)
    }
}