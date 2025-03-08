package com.ruzibekov.alphazettv.presentation.main.state

import com.ruzibekov.alphazettv.domain.model.AppInfo

data class MainState(
    val apps: List<AppInfo> = emptyList(),
    val isLauncherEnabled: Boolean = true
)