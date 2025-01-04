package fuwafuwa.time.apps_info_impl.usecase

import fuwafuwa.time.core_data.dao.PermissionConfigDao
import javax.inject.Inject

class GetPermissionConfigUseCase @Inject constructor(
    permissionConfigDao: PermissionConfigDao,
) {

    val permissionConfig = permissionConfigDao.getPermissionConfig()
}