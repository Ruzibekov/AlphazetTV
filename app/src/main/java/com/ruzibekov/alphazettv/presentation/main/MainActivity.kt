package com.ruzibekov.alphazettv.presentation.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.ruzibekov.alphazettv.presentation.main.content.MainContent
import com.ruzibekov.alphazettv.presentation.theme.AlphazetTVTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlphazetTVTheme {
                val state = viewModel.state.collectAsState()
                MainContent.Default(state.value, viewModel::sendAction)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetch()
        if (!viewModel.state.value.isLauncherEnabled)
            startActivity(Intent(Settings.ACTION_HOME_SETTINGS))
    }
}