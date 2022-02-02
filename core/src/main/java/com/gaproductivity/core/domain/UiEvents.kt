package com.gaproductivity.core.domain

sealed class UiEvents {
    object PopBackStack: UiEvents()
    data class Navigate(val route: String): UiEvents()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvents()
}
