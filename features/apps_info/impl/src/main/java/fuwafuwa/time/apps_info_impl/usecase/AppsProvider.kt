package fuwafuwa.time.apps_info_impl.usecase

import android.content.pm.PackageManager
import fuwafuwa.time.core.model.app.App
import javax.inject.Inject

class AppsProvider @Inject constructor(
    private val packageManager: PackageManager
) {

    fun provide(): List<App> {
        val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        return packages.map { appInfo ->
            App(
                name = appInfo.processName
            )
        }
    }
}