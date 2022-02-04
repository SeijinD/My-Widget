package eu.seijindemon.mywidget.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

const val Datastore_Name = "MyDatabase"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Datastore_Name)

class DataStorePreferenceRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val LANGUAGE = stringPreferencesKey("LanguageData")
    }

    suspend fun setLanguage(language: Int) {
        context.datastore.edit { preferences ->
            preferences[LANGUAGE] = language.toString()
        }
    }

    suspend fun getLanguage() = context.datastore.data.map { preferences ->
        preferences[LANGUAGE]?.toInt() ?: 0
    }

//    private val languageDataStore: DataStore<Preferences> = context.createDataStore(name = "LanguageData")
//    private val defaultLanguage = 0
//
//    private val themeDataStore: DataStore<Preferences> = context.createDataStore(name = "ThemeData")
//    private val defaultTheme = 0
//
//    companion object {
//        val PREF_LANGUAGE = preferencesKey<Int>("language")
//        val PREF_THEME = preferencesKey<Int>("theme")
//
//        private var INSTANCE: DataStorePreferenceRepository? = null
//
//        fun getInstance(context: Context): DataStorePreferenceRepository {
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE?.let {
//                    return it
//                }
//                val instance = DataStorePreferenceRepository(context = context)
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//
//    suspend fun setLanguage(language: Int) {
//        languageDataStore.edit { preferences ->
//            preferences[PREF_LANGUAGE] = language
//        }
//    }
//
//    val getLanguage: Flow<Int> = languageDataStore.data
//        .map {  preferences ->
//            preferences[PREF_LANGUAGE] ?: defaultLanguage
//        }
}