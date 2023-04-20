package com.jaysdevapp.stickymemory

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


class NotificationHelper(base: Context?) : ContextWrapper(base) {
    private var notiManager: NotificationManager? = null

    init {

        //안드로이드 버전이 오레오거나 이상이면 채널생성성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createChannels() {
        val channel1 =
            NotificationChannel(channeID, channeNm, NotificationManager.IMPORTANCE_DEFAULT)
        channel1.enableLights(true) //불빛
        channel1.enableVibration(true)  //진동
        channel1.lightColor = R.color.holo_red_light    //색상
        channel1.lockscreenVisibility = Notification.VISIBILITY_PRIVATE //잠금

        manager!!.createNotificationChannel(channel1)
    }

    val manager: NotificationManager?
        get() {
            if (notiManager == null) {
                notiManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            }
            return notiManager
        }
    val channelNotification: NotificationCompat.Builder
        get() = NotificationCompat.Builder(applicationContext, channeID)
            .setContentTitle("알람")
            .setContentText("알람매니저 실행중")
            .setSmallIcon(R.mipmap.sym_def_app_icon)





    companion object {
        const val channeID = "channelID"
        const val channeNm = "channelNm"
    }
}