package com.gaproductivity.doitall.domain.event

sealed class MenuEvent {
    object ToggleMenu: MenuEvent()
    object ExitApp: MenuEvent()
}
