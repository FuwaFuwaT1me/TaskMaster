package fuwafuwa.time.taskmaster.permission

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import fuwafuwa.time.utli.permission.PermissionHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PermissionInitializer @Inject constructor(
    private val permissionConfigUpdater: PermissionConfigUpdater
) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun init(activity: ComponentActivity) {
        val activityResultLauncher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            Log.d("ANIME", "result")
            coroutineScope.launch {
                permissionConfigUpdater.update()
            }
        }

        PermissionHolder.setActivityResultLauncher(activityResultLauncher)
    }
}