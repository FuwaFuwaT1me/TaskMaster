package fuwafuwa.time.apps_info_impl.usecase

import fuwafuwa.time.apps_info_impl.app.AppsProvider
import fuwafuwa.time.core_data.dao.AppDao
import fuwafuwa.time.core_data.entity.app.toDto
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val appDao: AppDao,
    private val appsProvider: AppsProvider
) {

    val apps = appDao.getApps()

    suspend fun getApps() {
        appDao.putApps(appsProvider.provide().map { it.toDto() })
    }
}