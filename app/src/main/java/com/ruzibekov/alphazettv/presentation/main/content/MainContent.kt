package com.ruzibekov.alphazettv.presentation.main.content

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.ruzibekov.alphazettv.domain.model.AppInfo
import com.ruzibekov.alphazettv.presentation.main.state.MainAction
import com.ruzibekov.alphazettv.presentation.main.state.MainState
import com.ruzibekov.alphazettv.R

object MainContent {

    @Composable
    fun Default(
        state: MainState,
        sendAction: (MainAction) -> Unit,
    ) {
        Crossfade(state.isLauncherEnabled, label = "main screen") { enabled ->
            if (enabled)
                AppsView(
                    apps = state.apps,
                    onItemClick = {
                        sendAction(MainAction.OnAppClick(it))
                    }
                )
            else
                EnableView()
        }
    }

    @Composable
    private fun AppsView(
        apps: List<AppInfo>,
        onItemClick: (AppInfo) -> Unit
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(apps) { app ->
                Item(
                    app = app,
                    onClick = { onItemClick(app) },
                    modifier = Modifier
                        .padding(8.dp)
                        .focusable()
                )
            }
        }
    }

    @OptIn(ExperimentalTvMaterial3Api::class)
    @Composable
    private fun Item(
        app: AppInfo,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .clickable(onClick = onClick)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                bitmap = app.icon.toBitmap().asImageBitmap(),
                contentDescription = app.name,
                modifier = Modifier.size(64.dp)
            )

            Text(
                text = app.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

    @OptIn(ExperimentalTvMaterial3Api::class)
    @Composable
    private fun EnableView() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.enable_launcher),

                    )
            }
        }
    }
}