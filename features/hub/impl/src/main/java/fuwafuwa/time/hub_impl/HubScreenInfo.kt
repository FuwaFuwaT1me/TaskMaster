package fuwafuwa.time.hub_impl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface HubScreenInfo {

    val title: String
    val icon: ImageVector

    data object AppsInfo : HubScreenInfo {

        override val title: String = "Apps info"
        override val icon: ImageVector = Icons.Filled.Apps
    }

    data object Processes : HubScreenInfo {

        override val title: String = "Processes"
        override val icon: ImageVector = Icons.Filled.Timeline
    }
}