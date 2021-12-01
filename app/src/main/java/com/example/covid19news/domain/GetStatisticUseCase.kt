package com.example.covid19news.domain

import com.example.covid19news.common.Resource
import com.example.covid19news.domain.models.Statistic
import com.example.covid19news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStatisticUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(): Flow<Resource<Statistic>> = flow {
        try {
            emit(Resource.Loading())
            val statistic = repository.getStatistic()
            emit(Resource.Success(statistic))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}