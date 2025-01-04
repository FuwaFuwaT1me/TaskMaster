package fuwafuwa.time.utli.permission

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

object UsageStatsPermission : Permission {

    override val permissionType = PermissionType.UsageStats

    override fun grantIfNeeded(activity: ComponentActivity) {
        if (!checkIsGranted(activity)) {
            launchIntent()
        }
    }

    override fun checkIsGranted(context: Context) : Boolean {
        val appOpsManager = context.getSystemService(AppCompatActivity.APP_OPS_SERVICE)
                as AppOpsManager

        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOpsManager.unsafeCheckOpNoThrow(
                "android:get_usage_stats",
                android.os.Process.myUid(), "fuwafuwa.time.taskmaster"
            )
        } else {
            appOpsManager.checkOpNoThrow(
                "android:get_usage_stats",
                android.os.Process.myUid(), "fuwafuwa.time.taskmaster"
            )
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun launchIntent() {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        val activityResultLauncher = PermissionHolder.getActivityResultLauncher()
        activityResultLauncher?.launch(intent)
    }
}