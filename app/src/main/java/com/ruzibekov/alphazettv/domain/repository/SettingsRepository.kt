package com.ruzibekov.alphazettv.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val isLauncherEnabled: Flow<Boolean>
    suspend fun setLauncherEnabled(enabled: Boolean)
}