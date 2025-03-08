package com.ruzibekov.alphazettv.domain.repository

import com.ruzibekov.alphazettv.domain.model.AppInfo
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getInstalledApps(): Flow<List<AppInfo>>
}