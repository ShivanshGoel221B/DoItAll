package com.gaproductivity.core.domain

import java.util.*

object Converters {

    private val months = arrayOf(
        "JAN", "FEB", "MAR", "APR",
        "MAY", "JUN", "JULY", "AUG",
        "SEP", "OCT", "NOV", "DEC"
    )
    private const val AM = "AM"
    private const val PM = "PM"

    fun getFormattedDate(
        timeInMillis: Long
    ): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]
        val formattedDay = getModifierNumber(day)
        val formattedMonth = months[month]
        return "$formattedMonth $formattedDay, $year"
    }

    fun getFormattedTime(
        timeInMillis: Long
    ): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis

        var hour = calendar[Calendar.HOUR_OF_DAY]
        val minute = calendar[Calendar.MINUTE]
        val specifier = getTimeSpecifier(hour)
        if (hour > 12) hour -= 12
        if(hour == 0) hour = 12
        val modifiedHour = getModifierNumber(hour)
        val modifiedMinute = getModifierNumber(minute)

        return "$modifiedHour : $modifiedMinute $specifier"
    }

    private fun getTimeSpecifier(
        hour: Int
    ): String {
        return if (hour >= 12)
            PM
        else
            AM
    }

    private fun getModifierNumber(number: Int): String =
        if (number < 10) "0$number" else number.toString()
}