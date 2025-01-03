package fuwafuwa.time.core_data.entity.app

import fuwafuwa.time.core.model.app.App

fun App.toDto() = AppDto(
    name = name,
    packageName = packageName,
    processName = processName,
    apkSize = apkSize,
    appFolderSize = appFolderSize,
    iconPath = iconPath
)

fun AppDto.toModel() = App(name, packageName, processName, apkSize, appFolderSize, iconPath)
