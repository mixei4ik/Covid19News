package com.example.covid19news.data

import com.example.covid19news.common.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}
