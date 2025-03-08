package com.ruzibekov.alphazettv.data.repository

import com.ruzibekov.alphazettv.data.store.SettingsDataStore
import com.ruzibekov.alphazettv.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {
    override val isLauncherEnabled = settingsDataStore.isLauncherEnabled
    override suspend fun setLauncherEnabled(enabled: Boolean) = settingsDataStore.setLauncherEnabled(enabled)
}