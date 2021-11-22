package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.data.repository.NewsRepositoryImpl
import com.example.covid19news.domain.GetNewsUseCase
import com.example.covid19news.domain.NewsModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel: ViewModel() {

    private val _items = MutableStateFlow<List<NewsModel>?>(null)
    val items: StateFlow<List<NewsModel>?> = _items.asStateFlow()

    private val newsRepository by lazy { NewsRepositoryImpl() }

    var getNewsUseCase = GetNewsUseCase(newsRepository)


    init {
        viewModelScope.launch {
            val result = getNewsUseCase()
            _items.value = result
        }
    }
}