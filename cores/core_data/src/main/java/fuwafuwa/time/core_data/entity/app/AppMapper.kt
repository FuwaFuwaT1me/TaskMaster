package fuwafuwa.time.core_data.entity.app

import fuwafuwa.time.core.model.app.App

fun App.toDto() = AppDto(name = name)

fun AppDto.toModel() = App(name)
