package com.nitezhang.wetime.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent


/**
 * AlarmManager工具类
 *
 */

object AlarmManagerUtil {
    // 获取AlarmManager实例
    private fun getAlarmManager(context: Context): AlarmManager {
        return context.getSystemService(ALARM_SERVICE) as AlarmManager
    }

    // 发送定时广播（执行广播中的定时任务）
    // 参数：
    // context:上下文
    // requestCode:请求码，用于区分不同的任务
    // type:alarm启动类型
    // triggerAtTime:定时任务开启的时间，毫秒为单位
    // cls:广播接收器的class
    fun sendAlarmBroadcast(
        context: Context, requestCode: Int,
        type: Int, triggerAtTime: Long, content: String, cls: Class<*>
    ) {
        val mgr = getAlarmManager(context)

        val intent = Intent(context, cls)
        intent.putExtra("content", content)
        val pi = PendingIntent.getBroadcast(
            context, requestCode,
            intent, 0
        )

        mgr.set(type, triggerAtTime, pi)
    }

    // 取消指定requestCode的定时任务
    // 参数：
    // context:上下文
    // requestCode:请求码，用于区分不同的任务
    // cls:广播接收器的class
    fun cancelAlarmBroadcast(
        context: Context, requestCode: Int,
        cls: Class<*>
    ) {
        val mgr = getAlarmManager(context)

        val intent = Intent(context, cls)
        val pi = PendingIntent.getBroadcast(
            context, requestCode,
            intent, 0
        )

        mgr.cancel(pi)
//        ToastUtil
//            .showShort(context, "取消定时服务成功 @requestCode:$requestCode")
        NLog.d("取消定时服务成功", "@requestCode:$requestCode")
    }

    // 周期性执行定时任务
    // 参数：
    // context:上下文
    // requestCode:请求码，用于区分不同的任务
    // type:alarm启动类型
    // startTime:开始的时间，毫秒为单位
    // cycleTime:定时任务的重复周期，毫秒为单位
    // cls:广播接收器的class
    fun sendRepeatAlarmBroadcast(
        context: Context,
        requestCode: Int, type: Int, startTime: Long, cycleTime: Long, cls: Class<*>
    ) {
        val mgr = getAlarmManager(context)

        val intent = Intent(context, cls)
        val pi = PendingIntent.getBroadcast(
            context, requestCode,
            intent, 0
        )

        mgr.setRepeating(type, startTime, cycleTime, pi)
    }
}