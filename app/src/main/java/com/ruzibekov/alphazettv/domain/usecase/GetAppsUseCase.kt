package com.ruzibekov.alphazettv.domain.usecase

import com.ruzibekov.alphazettv.domain.model.AppInfo
import com.ruzibekov.alphazettv.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    operator fun invoke(): Flow<List<AppInfo>> = appRepository.getInstalledApps()
}