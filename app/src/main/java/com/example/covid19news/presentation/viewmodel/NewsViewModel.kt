package com.example.covid19news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid19news.domain.GetNewsUseCase
import com.example.covid19news.domain.models.NewsModel
import com.example.covid19news.domain.SaveNewsUseCase
import com.example.covid19news.domain.DeleteSavedNewsUseCase
import com.example.covid19news.domain.GetSavedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
): ViewModel() {

    private val _items = MutableStateFlow<List<NewsModel>?>(null)
    val items: StateFlow<List<NewsModel>?> = _items.asStateFlow()


//    private val _savedItems = MutableStateFlow<List<NewsModel>?>(null)
    val savedItems: StateFlow<List<NewsModel>> = getSavedNewsUseCase.getSavedNews().stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    init {
        getBreakingNews(country = "")
//        getSavedNews()
    }

    private fun getBreakingNews(country: String) = viewModelScope.launch {
            val result = getNewsUseCase.invoke(country)
            _items.value = result
        }

    fun saveNews(news: NewsModel) = viewModelScope.launch {
        saveNewsUseCase.saveNews(news)
    }


/*    private fun getSavedNews() = viewModelScope.launch {
        savedItems = getSavedNewsUseCase.getSavedNews().stateIn(viewModelScope, SharingStarted.Eagerly, listOf())
    }*/

    fun deleteSavedNews(news: NewsModel) = viewModelScope.launch {
        deleteSavedNewsUseCase.deleteSavedNews(news)
    }
}