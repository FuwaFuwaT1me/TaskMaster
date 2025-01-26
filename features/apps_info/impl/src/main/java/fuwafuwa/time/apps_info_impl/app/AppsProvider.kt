package fuwafuwa.time.apps_info_impl.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.graphics.drawable.toBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core.model.app.AppSize
import fuwafuwa.time.core.model.app.CacheSize
import fuwafuwa.time.core.model.app.DataSize
import fuwafuwa.time.utli.bitmap.saveAsPngToFile
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.io.File
import javax.inject.Inject

class AppsProvider @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val packageManager: PackageManager,
    private val appStorageStatsProvider: AppStorageStatsProvider =
        AppStorageStatsProvider(applicationContext)
) {

    @SuppressLint("QueryPermissionsNeeded")
    suspend fun provide(): List<App> {
        return coroutineScope {
            val packages = packageManager.getInstalledApplications(0)

            packages.map { appInfo ->
                async {
                    val storageStats = appStorageStatsProvider.get(appInfo)
                    val packageInfo = packageManager.getPackageInfo(appInfo.packageName, 0)

                    App(
                        name = getAppName(appInfo),
                        packageName = appInfo.packageName,
                        processName = appInfo.processName,
                        apkSize = getFolderSizeMb(appInfo.sourceDir),
                        appSize = AppSize(storageStats.appBytes),
                        dataSize = DataSize(storageStats.dataBytes - storageStats.cacheBytes),
                        cacheSize = CacheSize(storageStats.cacheBytes),
                        totalSize = storageStats.appBytes + storageStats.dataBytes,
                        iconPath = getIconImagePath(appInfo),
                        versionName = packageInfo.versionName,
                        versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            packageInfo.longVersionCode
                        } else {
                            packageInfo.versionCode.toLong()
                        }
                    )
                }
            }.map { deferred ->
                deferred.await()
            }
        }
    }

    private fun getAppName(appInfo: ApplicationInfo): String {
        val label = appInfo.loadLabel(packageManager).toString()
        return label.ifEmpty {
            appInfo.packageName
        }
    }

    private fun getIconImagePath(appInfo: ApplicationInfo): String {
        val filesDir = applicationContext.filesDir
        val file = File("${filesDir.absolutePath}/${appInfo.packageName}.png")
        val bitmap = appInfo.loadIcon(packageManager).toBitmap()

        bitmap.saveAsPngToFile(file)

        return file.absolutePath
    }

    private fun getFolderSizeMb(path: String): Long {
        return File(path).length()
    }

    private companion object {

        private const val MB_CONVERSION_FACTOR = 1000.0 * 1000
    }
}