package com.gaproductivity.components.presentation.utils

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.core.content.ContextCompat.startActivity
import javax.inject.Inject


class Reminder @Inject constructor(
    private val app: Application
) {
    fun createReminder(
        timiInMillis: Long,
        title: String
    ) {
        val intent = Intent(Intent.ACTION_EDIT)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra("beginTime", timiInMillis)
        intent.putExtra("allDay", false)
        intent.putExtra("rrule", "FREQ=ONCE")
        intent.putExtra("endTime", timiInMillis + 1 * 60 * 1000)
        intent.putExtra("title", title)
        startActivity(app, intent, null)
    }
}
