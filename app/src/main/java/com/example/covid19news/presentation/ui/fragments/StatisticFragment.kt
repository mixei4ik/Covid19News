package com.example.covid19news.presentation.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.covid19news.R
import com.example.covid19news.databinding.FragmentStatisticBinding
import com.example.covid19news.domain.models.Statistic
import com.example.covid19news.presentation.ui.MainActivity
import com.example.covid19news.presentation.viewmodel.StatisticViewModel
import kotlinx.coroutines.flow.collectLatest

class StatisticFragment : Fragment(R.layout.fragment_statistic) {

    private lateinit var binding: FragmentStatisticBinding

    lateinit var statisticViewModel: StatisticViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatisticBinding.bind(view)
        statisticViewModel = (activity as MainActivity).statisticViewModel

        lifecycleScope.launchWhenStarted {
            statisticViewModel.statisticState.collectLatest {
                if (it.isLoading) {
                    binding.progressStatistic.isVisible = true
                } else {
                    binding.progressStatistic.isVisible = false
                    bindStatistic(it.statistic)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindStatistic(statistic: Statistic?) {
        if (statistic == null) return
        binding.totalConfirmedView.text = "Total confirmed : ${statistic.totalConfirmed}"
        binding.totalDeathsView.text = "Total deaths : ${statistic.totalDeaths}"
        binding.totalRecoveredView.text = "Total recovered : ${statistic.totalRecovered}"
        binding.totalNewCasesView.text = "Total new cases : ${statistic.totalNewCases}"
        binding.totalActiveCasesView.text = "Total active cases : ${statistic.totalActiveCases}"
        binding.totalCasesPerMillionPopView.text =
            "Total cases per million pop : ${statistic.totalCasesPerMillionPop}"
        binding.updatedTimeView.text = "Updated time : ${statistic.created}"
    }
}