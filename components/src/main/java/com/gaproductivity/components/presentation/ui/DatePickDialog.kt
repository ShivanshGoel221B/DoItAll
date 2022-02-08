package com.gaproductivity.components.presentation.ui

import android.app.DatePickerDialog
import android.content.Context
import java.util.*

fun datePickerDialog(
    context: Context,
    value: Calendar,
    minDate: Long,
    onChange: (Calendar) -> Unit
) {
    val dateDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            onChange(calendar)
        },
        value[Calendar.YEAR], value[Calendar.MONTH], value[Calendar.DAY_OF_MONTH]
    )
    dateDialog.datePicker.minDate = minDate
    dateDialog.show()

}