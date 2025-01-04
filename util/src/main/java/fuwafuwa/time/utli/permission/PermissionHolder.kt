package fuwafuwa.time.utli.permission

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

object PermissionHolder {

    private var ACTIVITY_RESULT_LAUNCHER: ActivityResultLauncher<Intent>? = null

    val permissions = listOf(
        UsageStatsPermission
    )

    fun setActivityResultLauncher(activityResultLauncher: ActivityResultLauncher<Intent>) {
        ACTIVITY_RESULT_LAUNCHER = activityResultLauncher
    }

    fun getActivityResultLauncher(): ActivityResultLauncher<Intent>? {
        return ACTIVITY_RESULT_LAUNCHER
    }
}