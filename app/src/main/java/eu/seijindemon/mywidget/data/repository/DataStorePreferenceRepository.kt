package eu.seijindemon.mywidget.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val Datastore_Name = "MyDatabase"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Datastore_Name)

class DataStorePreferenceRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val LANGUAGE = stringPreferencesKey("LanguageData")
        val PHONE = stringPreferencesKey("PhoneData")
        val TELEGRAM = stringPreferencesKey("TelegramData")
    }

    // Language
    suspend fun setLanguage(language: Int) {
        context.datastore.edit { preferences ->
            preferences[LANGUAGE] = language.toString()
        }
    }

    fun getLanguage() = context.datastore.data.map { preferences ->
        preferences[LANGUAGE]?.toInt() ?: 0
    }

    // Phone
    suspend fun setPhone(phone: String) {
        context.datastore.edit { preferences ->
            preferences[PHONE] = phone
        }
    }

    fun getPhone() = context.datastore.data.map { preferences ->
        preferences[PHONE] ?: ""
    }

    // Telegram
    suspend fun setTelegram(telegram: String) {
        context.datastore.edit { preferences ->
            preferences[TELEGRAM] = telegram
        }
    }

    fun getTelegram() = context.datastore.data.map { preferences ->
        preferences[TELEGRAM] ?: ""
    }

}