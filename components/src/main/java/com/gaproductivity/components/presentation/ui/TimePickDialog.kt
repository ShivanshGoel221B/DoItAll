package com.gaproductivity.components.presentation.ui

import android.app.TimePickerDialog
import android.content.Context
import com.gaproductivity.core.domain.Converters
import java.util.*

fun timePickerDialog(
    context: Context,
    value: Calendar,
    onChange: (Calendar) -> Unit
) {
    val timeDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            value[Calendar.HOUR_OF_DAY] = hourOfDay
            value[Calendar.MINUTE] = minute
            onChange(value)
            println(Converters.getFormattedTime(value.timeInMillis))
        },
        value[Calendar.HOUR_OF_DAY], value[Calendar.MINUTE], false
    )
    timeDialog.show()
}