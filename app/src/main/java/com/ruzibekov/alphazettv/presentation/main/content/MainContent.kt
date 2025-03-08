package com.ruzibekov.alphazettv.presentation.main.content

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.NonInteractiveSurfaceDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.ruzibekov.alphazettv.R
import com.ruzibekov.alphazettv.domain.model.AppInfo
import com.ruzibekov.alphazettv.presentation.components.ButtonView
import com.ruzibekov.alphazettv.presentation.main.state.MainAction
import com.ruzibekov.alphazettv.presentation.main.state.MainState

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
                    },
                    onDisableClick = {
                        sendAction(MainAction.OpenHomeLaunchSettings)
                    }
                )
            else
                EnableView(onClick = {
                    sendAction(MainAction.OpenHomeLaunchSettings)
                })
        }
    }

    @Composable
    private fun AppsView(
        apps: List<AppInfo>,
        onItemClick: (AppInfo) -> Unit,
        onDisableClick: () -> Unit,
    ) {
        var showDialog = remember { mutableStateOf(false) }

        Column {
            TopBarView(onSettingsClick = {
                showDialog.value = true
            })

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

        if (showDialog.value)
            DisableLauncherDialog(
                onDoneClick = {
                    onDisableClick()
                },
                onDismiss = {
                    showDialog.value = false
                },
            )
    }

    @OptIn(ExperimentalTvMaterial3Api::class)
    @Composable
    fun DisableLauncherDialog(onDoneClick: () -> Unit, onDismiss: () -> Unit) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                colors = NonInteractiveSurfaceDefaults.colors(containerColor = Color.Gray)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(R.string.disable_launcher_text),
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    ButtonView.Default(
                        onClick = {
                            onDoneClick()
                            onDismiss()
                        },
                        textRes = R.string.done_btn
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalTvMaterial3Api::class)
    @Composable
    private fun TopBarView(onSettingsClick: () -> Unit) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                painter = painterResource(R.drawable.ic_settings),
                contentDescription = "settings icon",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(onClick = onSettingsClick)
                    .padding(6.dp)
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
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

    @Composable
    private fun EnableView(onClick: () -> Unit) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ButtonView.Default(
                onClick = onClick,
                textRes = R.string.enable_launcher
            )
        }
    }
}