package com.ruzibekov.alphazettv.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruzibekov.alphazettv.data.managaer.LauncherManager
import com.ruzibekov.alphazettv.domain.model.AppInfo
import com.ruzibekov.alphazettv.domain.repository.SettingsRepository
import com.ruzibekov.alphazettv.domain.usecase.GetAppsUseCase
import com.ruzibekov.alphazettv.domain.usecase.LaunchAppUseCase
import com.ruzibekov.alphazettv.presentation.main.state.MainAction
import com.ruzibekov.alphazettv.presentation.main.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppsUseCase: GetAppsUseCase,
    private val launchAppUseCase: LaunchAppUseCase,
    private val settingsRepository: SettingsRepository,
    private val launcherManager: LauncherManager
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                getAppsUseCase(),
                settingsRepository.isLauncherEnabled
            ) { apps, isEnabled ->
                MainState(
                    apps = apps,
                    isLauncherEnabled = isEnabled
                )
            }.collect { state ->
                _state.value = state
            }
        }
    }

    fun sendAction(action: MainAction) = when(action) {
        is MainAction.OnAppClick -> launchAppUseCase(action.selectedApp.packageName)
    }

    fun toggleLauncher() {
        viewModelScope.launch {
            val newState = !_state.value.isLauncherEnabled
            settingsRepository.setLauncherEnabled(newState)
            launcherManager.setLauncherEnabled(newState)
        }
    }
}