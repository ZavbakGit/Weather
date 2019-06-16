package `fun`.gladkikh.app.myapplication.framework.preference

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHolder(private val context: Context) {

    fun getCurrentCity(): String {
        val defaultPrefs = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        return defaultPrefs.getString(KEY_CURRENT_CITY, "Moscow")?:"Moscow"
    }

    fun saveCity(city: String) {
        val defaultPrefs = PreferenceManager.getDefaultSharedPreferences(
            context
        )
        val editor = defaultPrefs.edit()

        editor.putString(KEY_CURRENT_CITY, city)
        editor.apply()
    }

    companion object {
        private const val KEY_CURRENT_CITY = "current_city"
    }

}