package com.jaysdevapp.stickymemory

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.text.DateFormat
import java.util.*

//알람 설정
fun startAlarm(c: Calendar, context: Context) {

    //알람매니저 선언
    var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    var intent = Intent(context, AlertReceiver::class.java)

    //데이터 담기
    var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)
    intent.putExtra("time", curTime)

    var pendingIntent = PendingIntent.getBroadcast(context, 1, intent, FLAG_IMMUTABLE)

    //설정 시간이 현재시간 이전이면 +1일
    if (c.before(Calendar.getInstance())) {

        c.add(Calendar.DATE, 1)
    }

    alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
}

class AlertReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationHelper = NotificationHelper(context)
        val nb = notificationHelper.channelNotification
        notificationHelper.manager!!.notify(1, nb.build())
    }
}


fun cancelAlarm(context: Context) {
    //알람매니저 선언
    var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    var intent = Intent(context, AlertReceiver::class.java)

    var pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0)

    alarmManager.cancel(pendingIntent)
}