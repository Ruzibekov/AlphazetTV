package com.ruzibekov.alphazettv.data

import android.content.Context
import android.content.Intent
import com.ruzibekov.alphazettv.domain.AppInfo
import com.ruzibekov.alphazettv.domain.AppRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val context: Context
) : AppRepository {

    override fun getInstalledApps() = flow {
        val packageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER)
        }

        val apps = packageManager.queryIntentActivities(intent, 0)
            .map { resolveInfo ->
                AppInfo(
                    name = resolveInfo.loadLabel(packageManager).toString(),
                    packageName = resolveInfo.activityInfo.packageName,
                    icon = resolveInfo.loadIcon(packageManager)
                )
            }
            .sortedBy { it.name }

        emit(apps)
    }
}