package fuwafuwa.time.apps_info_impl.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.core.graphics.drawable.toBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.utli.bitmap.saveAsPngToFile
import java.io.File
import javax.inject.Inject

class AppsProvider @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val packageManager: PackageManager,
    private val appStorageStatsProvider: AppStorageStatsProvider =
        AppStorageStatsProvider(applicationContext)
) {

    @SuppressLint("QueryPermissionsNeeded")
    fun provide(): List<App> {
        val packages = packageManager.getInstalledApplications(0)

        return packages.map { appInfo ->
            val storageStats = appStorageStatsProvider.get(appInfo)

            App(
                name = getAppName(appInfo),
                packageName = appInfo.packageName,
                processName = appInfo.processName,
                apkSize = getFolderSizeMb(appInfo.sourceDir),
                appSize = storageStats.appBytes / MB_CONVERSION_FACTOR,
                dataSize = (storageStats.dataBytes - storageStats.cacheBytes) / MB_CONVERSION_FACTOR,
                cacheSize = storageStats.cacheBytes / MB_CONVERSION_FACTOR,
                iconPath = getIconImagePath(appInfo)
            )
        }
    }

    private fun getAppName(appInfo: ApplicationInfo): String? {
        return appInfo.loadLabel(packageManager).toString().takeIf {
            it.isNotEmpty() && it != appInfo.packageName
        }
    }

    private fun getIconImagePath(appInfo: ApplicationInfo): String {
        val filesDir = applicationContext.filesDir
        val file = File("${filesDir.absolutePath}/${appInfo.packageName}.png")
        val bitmap = appInfo.loadIcon(packageManager).toBitmap()

        bitmap.saveAsPngToFile(file)

        return file.absolutePath
    }

    private fun getFolderSizeMb(path: String): Double {
        return File(path).length() / MB_CONVERSION_FACTOR
    }

    private companion object {

        private const val MB_CONVERSION_FACTOR = 1000.0 * 1000
    }
}