package com.example.focus.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import com.example.focus.settings.GlobalSettings
import javax.inject.Inject

class AlarmManagerUtil @Inject constructor(private val context: Context, private val globalSettings: GlobalSettings) {
    private var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun sendFinishedSessionNotification(millisInFuture: Long) {
        if(!globalSettings.notificationsEnabled) {
            return
        }
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + millisInFuture, pendingIntent)
        }
        else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime() + millisInFuture, pendingIntent)
        }

    }
}
