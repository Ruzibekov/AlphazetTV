package com.ruzibekov.alphazettv.data.store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataStore @Inject constructor(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore("launcher_settings")

    private val isEnabledKey = booleanPreferencesKey("is_launcher_enabled")

    val isLauncherEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[isEnabledKey] == true }

    suspend fun setLauncherEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[isEnabledKey] = enabled
        }
    }
}