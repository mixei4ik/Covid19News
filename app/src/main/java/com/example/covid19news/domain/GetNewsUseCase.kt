package com.example.covid19news.domain

import com.example.covid19news.common.Resource
import com.example.covid19news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(country: String): Flow<Resource<List<NewsModel>>> = flow {
        try {
            emit(Resource.Loading())
            val news = repository.getAllNews(country)
            emit(Resource.Success(news))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}