package com.example.covid19news.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.covid19news.R
import com.example.covid19news.databinding.FragmentSettingBinding
import com.example.covid19news.presentation.ui.MainActivity
import com.example.covid19news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest

class SettingFragment : Fragment(R.layout.fragment_setting) {

    private lateinit var binding: FragmentSettingBinding

    lateinit var newsViewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.bind(view)

        newsViewModel = (activity as MainActivity).newsViewModel

        lifecycleScope.launchWhenStarted {
            newsViewModel.darkThemeOn.collectLatest {
                binding.switchCompat.isChecked = it
            }
        }

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
