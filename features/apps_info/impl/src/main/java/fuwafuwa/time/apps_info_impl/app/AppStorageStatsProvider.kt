package fuwafuwa.time.apps_info_impl.app

import android.app.usage.StorageStats
import android.app.usage.StorageStatsManager
import android.content.Context
import android.content.Context.STORAGE_STATS_SERVICE
import android.content.pm.ApplicationInfo
import android.os.Process
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppStorageStatsProvider @Inject constructor(
    @ApplicationContext context: Context
) {

    private val storageStatsManager = context.getSystemService(STORAGE_STATS_SERVICE)
        as StorageStatsManager

    fun get(appInfo: ApplicationInfo): StorageStats {
        return storageStatsManager.queryStatsForPackage(
            appInfo.storageUuid,
            appInfo.packageName,
            Process.myUserHandle()
        )
    }
}