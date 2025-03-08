package com.ruzibekov.alphazettv.domain.usecase

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CheckLaunchingUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(): Boolean {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
        }
        val defaultHomeActivity = context.packageManager?.resolveActivity(
            intent, PackageManager.MATCH_DEFAULT_ONLY
        )
        return defaultHomeActivity?.activityInfo?.packageName == context.packageName
    }
}
