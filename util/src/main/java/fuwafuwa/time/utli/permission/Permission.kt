package fuwafuwa.time.utli.permission

import android.content.Context
import androidx.activity.ComponentActivity

interface Permission {

    val permissionType: PermissionType

    fun grantIfNeeded(activity: ComponentActivity)

    fun checkIsGranted(context: Context): Boolean
}