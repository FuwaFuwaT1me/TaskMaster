package fuwafuwa.time.apps_info_impl.usecase

import fuwafuwa.time.core.model.app.App
import fuwafuwa.time.core_data.dao.AppDao
import fuwafuwa.time.core_data.entity.app.toModel
import javax.inject.Inject

class SearchForAppsUseCase @Inject constructor() {

    fun search(apps: List<App>, searchString: String): List<App> {
        return apps.filter { app ->
            app.name.contains(searchString) || app.packageName.contains(searchString)
        }
    }
}