package fuwafuwa.time.taskmaster.permission

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import fuwafuwa.time.core.model.permission.PermissionConfig
import fuwafuwa.time.core_data.dao.PermissionConfigDao
import fuwafuwa.time.core_data.entity.permission.toDto
import fuwafuwa.time.utli.permission.PermissionHolder
import fuwafuwa.time.utli.permission.PermissionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PermissionConfigUpdater @Inject constructor(
    @ApplicationContext private val context: Context,
    private val permissionConfigDao: PermissionConfigDao,
) {

    suspend fun update() {
        val permissionConfigBuilder = PermissionConfig.Builder()

        PermissionHolder.permissions.forEach {
            val isPermissionGranted = it.checkIsGranted(context)

            when (it.permissionType) {
                PermissionType.UsageStats -> permissionConfigBuilder.setUsageStats(isPermissionGranted)
            }
        }

        val permissionConfig = permissionConfigBuilder.build()
        withContext(Dispatchers.IO) {
            permissionConfigDao.putPermissionConfig(permissionConfig.toDto())
        }
    }
}