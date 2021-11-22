package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.data.repository.NewsRepositoryImpl
import com.example.covid19news.domain.GetNewsUseCase
import com.example.covid19news.domain.NewsModel
import com.example.covid19news.domain.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private val _items = MutableLiveData<List<NewsModel>>()
    val items: LiveData<List<NewsModel>> get() = _items

    private val newsRepository by lazy { NewsRepositoryImpl() }

    var getNewsUseCase = GetNewsUseCase(newsRepository)


    init {
        viewModelScope.launch {
            val result = getNewsUseCase()
            _items.postValue(result)
        }
    }
}