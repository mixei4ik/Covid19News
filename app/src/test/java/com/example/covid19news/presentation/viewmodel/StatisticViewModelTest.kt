package com.example.covid19news.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.covid19news.MainCoroutineRule
import com.example.covid19news.domain.GetStatisticUseCase
import com.example.covid19news.domain.models.Statistic
import com.example.covid19news.domain.repository.FakeNewsRepository
import com.example.covid19news.presentation.StatisticState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class StatisticViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: StatisticViewModel

    @Before
    fun setup() {
        viewModel = StatisticViewModel(GetStatisticUseCase(FakeNewsRepository()))
    }

    @Test
    fun `get statistical use case with correct parameters returns true`() = runBlockingTest {
        viewModel.getStatistic()

        val result = arrayListOf<StatisticState>()
        val statisticRep = StatisticState(
            false,
            Statistic(1, 1, 1, 1, 1, 1, 1, ""),
            ""
        )

        val job = launch {
            viewModel.statisticState.toList(result)
        }

        assertThat(result).isEqualTo(statisticRep)
        job.cancel()
    }
}
