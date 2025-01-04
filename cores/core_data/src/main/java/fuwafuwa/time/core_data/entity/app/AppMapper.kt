package fuwafuwa.time.core_data.entity.app

import fuwafuwa.time.core.model.app.App

fun App.toDto() = AppDto(
    name = name,
    packageName = packageName,
    processName = processName,
    apkSize = apkSize,
    appSize = appSize,
    dataSize = dataSize,
    cacheSize = cacheSize,
    iconPath = iconPath,
    versionName = versionName,
    versionCode = versionCode,
)

fun AppDto.toModel() = App(
    name,
    packageName,
    processName,
    apkSize,
    appSize,
    dataSize,
    cacheSize,
    iconPath,
    versionName,
    versionCode,
)
