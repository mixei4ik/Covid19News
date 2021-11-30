package com.example.covid19news.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.covid19news.R
import com.example.covid19news.databinding.FragmentBreakingNewsBinding
import com.example.covid19news.databinding.FragmentSettingBinding
import com.example.covid19news.presentation.ui.MainActivity
import com.example.covid19news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest

class SettingFragment: Fragment(R.layout.fragment_setting) {

    private lateinit var binding: FragmentSettingBinding

    lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.bind(view)

        newsViewModel = (activity as MainActivity).newsViewModel

        lifecycleScope.launchWhenStarted {
            newsViewModel.darkThemeIncluded.collectLatest {
                binding.switchCompat.isChecked = it
            }
        }

//        binding.spinnerCountry.isSelected(R.array.country.  indexOf(userSettings.localization))

        binding.saveSettingsButton.setOnClickListener {
            val selectedDarkTheme = binding.switchCompat.isChecked
            val selectedLocalization = binding.spinnerCountry.selectedItem.toString()
            var localization = ""
            if (selectedLocalization != "ALL WORLD") localization = selectedLocalization
            newsViewModel.saveSetting(selectedDarkTheme, localization)
            newsViewModel.loadSettings()
            Toast.makeText(context, "Saved settings", Toast.LENGTH_SHORT).show()
        }
    }
}