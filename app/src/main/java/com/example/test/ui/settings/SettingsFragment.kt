package com.example.test.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.test.R

class SettingsFragment : Fragment() {

    private lateinit var themeSwitch: Switch
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        // Инициализация Switch
        themeSwitch = view.findViewById(R.id.switch1)

        // Установка начального состояния Switch на основе сохраненного предпочтения
        val isDarkTheme = sharedPreferences.getBoolean("dark_theme", false)
        themeSwitch.isChecked = isDarkTheme

        // Обработка изменения состояния Switch
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            // Сохранение предпочтения темы
            sharedPreferences.edit().putBoolean("dark_theme", isChecked).apply()
        }
    }
}
