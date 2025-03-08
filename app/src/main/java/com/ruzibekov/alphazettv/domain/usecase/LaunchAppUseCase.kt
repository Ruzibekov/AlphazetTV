package com.ruzibekov.alphazettv.domain.usecase

import android.content.Context
import android.content.Intent
import javax.inject.Inject
import kotlin.let

class LaunchAppUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(packageName: String) {
        val packageManager = context.packageManager
        val intent = packageManager.getLeanbackLaunchIntentForPackage(packageName)
            ?: packageManager.getLaunchIntentForPackage(packageName)

        intent?.let {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(it)
        }
    }
}