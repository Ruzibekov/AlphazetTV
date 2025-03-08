package com.ruzibekov.alphazettv.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.tv.material3.ExperimentalTvMaterial3Api
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
}