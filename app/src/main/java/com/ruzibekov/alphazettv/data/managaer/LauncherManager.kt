package com.ruzibekov.alphazettv.data.managaer

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import com.ruzibekov.alphazettv.presentation.main.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.jvm.java

class LauncherManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun setLauncherEnabled(enabled: Boolean) {
        val componentName = ComponentName(context, MainActivity::class.java)
        val packageManager = context.packageManager

        val newState = if (enabled) {
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        } else {
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        }

        packageManager.setComponentEnabledSetting(
            componentName,
            newState,
            PackageManager.DONT_KILL_APP
        )
    }
}