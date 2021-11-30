package com.example.covid19news.data.srorage

import android.content.Context
import com.example.covid19news.data.model.Settings

private const val SHARED_PREFS_SETTING = "shared_prefs_settings"
private const val KEY_DARK_THEME_INCLUDED = "darkThemeIncluded"
private const val KEY_LOCALIZATION = "localization"

class SharedPerfUserStorage(context: Context) : UserStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_SETTING, Context.MODE_PRIVATE)

    override fun saveSettings(settings: Settings): Boolean {
        sharedPreferences.edit().putBoolean(KEY_DARK_THEME_INCLUDED, settings.darkThemeIncluded)
            .apply()
        sharedPreferences.edit().putString(KEY_LOCALIZATION, settings.localization).apply()
        return true
    }

    override fun getSettings(): Settings {
        val darkThemeIncluded = sharedPreferences.getBoolean(KEY_DARK_THEME_INCLUDED, false)
        val localization = sharedPreferences.getString(KEY_LOCALIZATION, "") ?: ""
        return Settings(darkThemeIncluded, localization)
    }
}