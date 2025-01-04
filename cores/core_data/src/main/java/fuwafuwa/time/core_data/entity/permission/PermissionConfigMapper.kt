package fuwafuwa.time.core_data.entity.permission

import fuwafuwa.time.core.model.permission.PermissionConfig

fun PermissionConfigDto.toModel() = PermissionConfig(
    usageStatsPermission = usageStatsPermission
)

fun PermissionConfig.toDto() = PermissionConfigDto(
    usageStatsPermission = usageStatsPermission
)
