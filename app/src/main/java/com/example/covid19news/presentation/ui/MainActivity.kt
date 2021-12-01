package com.example.covid19news.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.covid19news.R
import com.example.covid19news.databinding.ActivityMainBinding
import com.example.covid19news.presentation.viewmodel.NewsViewModel
import com.example.covid19news.presentation.viewmodel.SavedNewsViewModel
import com.example.covid19news.presentation.viewmodel.StatisticViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    val newsViewModel by viewModels<NewsViewModel>()
    val savedNewsViewModel by viewModels<SavedNewsViewModel>()
    val statisticViewModel by viewModels<StatisticViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController)

        lifecycleScope.launchWhenStarted {
            newsViewModel.darkThemeIncluded.collectLatest {
                if (it) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting_button -> {
                navController.navigate(R.id.settingFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
