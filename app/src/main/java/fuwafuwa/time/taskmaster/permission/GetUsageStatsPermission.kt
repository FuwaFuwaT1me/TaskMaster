package fuwafuwa.time.taskmaster.permission

import android.app.Activity
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

class GetUsageStatsPermission : Permission {

    override fun grantIfNeeded(activity: Activity) {
        if (!checkUsageStatsPermission(activity)) {
            launchIntent(activity)
        }
    }

    private fun launchIntent(activity: Activity) {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        activity.startActivity(intent)
    }

    private fun checkUsageStatsPermission(context: Context) : Boolean {
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
}