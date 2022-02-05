package com.gaproductivity.core.domain

data class DialogModel(
    var title: String,
    var message: String,
    var negativeText: String = "Dismiss",
    var positiveText: String = "Confirm",
    var onDismiss: () -> Unit,
    var onNegative: () -> Unit = {},
    var onPositive: () -> Unit = {},
    var autoDismissible: Boolean = true
)
