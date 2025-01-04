package fuwafuwa.time.taskmaster.permission

import android.app.Activity

interface Permission {

    fun grantIfNeeded(activity: Activity)
}