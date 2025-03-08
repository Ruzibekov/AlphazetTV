package com.ruzibekov.alphazettv.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruzibekov.alphazettv.domain.usecase.CheckLaunchingUseCase
import com.ruzibekov.alphazettv.domain.usecase.GetAppsUseCase
import com.ruzibekov.alphazettv.domain.usecase.LaunchAppUseCase
import com.ruzibekov.alphazettv.domain.usecase.OpenHomeLaunchingSettingsUseCase
import com.ruzibekov.alphazettv.presentation.main.state.MainAction
import com.ruzibekov.alphazettv.presentation.main.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppsUseCase: GetAppsUseCase,
    private val checkLaunchingUseCase: CheckLaunchingUseCase,
    private val openHomeLaunchingSettingsUseCase: OpenHomeLaunchingSettingsUseCase,
    private val launchAppUseCase: LaunchAppUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    fun fetch() {
        viewModelScope.launch {
            if (checkLaunchingUseCase())
                getAppsUseCase().collect { apps ->
                    _state.value = MainState(apps, true)
                }
            else
                _state.value = MainState(emptyList(), false)
        }
    }

    fun sendAction(action: MainAction) = when (action) {
        is MainAction.OnAppClick -> launchAppUseCase(action.selectedApp.packageName)
        MainAction.OpenHomeLaunchSettings -> openHomeLaunchingSettingsUseCase()
    }
}