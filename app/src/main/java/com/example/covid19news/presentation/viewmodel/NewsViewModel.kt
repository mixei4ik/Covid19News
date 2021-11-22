package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.domain.GetNewsUseCase
import com.example.covid19news.domain.NewsModel
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {

    private val _items = MutableLiveData<List<NewsModel>>()
    val items: LiveData<List<NewsModel>> get() = _items

    var getNewsUseCase = GetNewsUseCase()


    init {
        viewModelScope.launch {
            val result = getNewsUseCase()
            _items.postValue(result)
        }
    }
}