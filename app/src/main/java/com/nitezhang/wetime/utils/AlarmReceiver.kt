package com.nitezhang.wetime.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.nitezhang.wetime.R


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
//        ToastUtil.showShort(
//            context,
//            "从服务启动广播：at " + DateTimeUtil.getCurrentDateTimeString()
//        )
        val content = intent.getStringExtra("content")
        NLog.d("Alarm", "从服务启动广播：at $content")
        val channelId = content
        val channelName = "channel_chat"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel =
                NotificationChannel(channelId, channelName, importance)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            val notification = NotificationCompat.Builder(context)
                .setContentTitle("时间到了")
                .setContentText(content)
                .setChannelId(channelId)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.icon_main_schedule_selected
                    )
                )
                .build()
            val notifiId = 1
            notificationManager.notify(notifiId, notification)
        }


    }

}