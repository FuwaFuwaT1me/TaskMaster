package fuwafuwa.time.core.model.permission

data class PermissionConfig(
    val usageStatsPermission: Boolean
) {

    class Builder(
        usageStatsPermission: Boolean = false
    ) {

        private var permissionConfig = PermissionConfig(
            usageStatsPermission = usageStatsPermission
        )

        fun setUsageStats(enabled: Boolean): Builder {
            permissionConfig = permissionConfig.copy(usageStatsPermission = enabled)
            return this
        }

        fun build(): PermissionConfig {
            return permissionConfig
        }
    }
}