package fuwafuwa.time.apps_info_impl.usecase

import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core_data.dao.AppDao
import fuwafuwa.time.core_data.entity.app.toModel
import javax.inject.Inject

class SearchForAppsUseCase @Inject constructor(
    private val appDao: AppDao
) {

    suspend fun search(searchString: String): List<App> {
        return appDao.getAppsBySubstring(searchString).map { it.toModel() }
    }
}