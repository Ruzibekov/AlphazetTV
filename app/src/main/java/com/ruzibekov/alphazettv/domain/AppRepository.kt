package com.ruzibekov.alphazettv.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getInstalledApps(): Flow<List<AppInfo>>
}