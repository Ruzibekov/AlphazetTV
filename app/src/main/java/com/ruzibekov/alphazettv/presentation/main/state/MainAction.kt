package com.ruzibekov.alphazettv.presentation.main.state

import com.ruzibekov.alphazettv.domain.model.AppInfo

sealed class MainAction {

    data class OnAppClick(val selectedApp: AppInfo) : MainAction()
    data object OpenHomeLaunchSettings : MainAction()
}