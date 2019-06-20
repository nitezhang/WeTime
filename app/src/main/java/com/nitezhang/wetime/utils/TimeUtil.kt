package com.nitezhang.wetime.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    fun getNoteTime(time: Long): String {
        if (time.isToday()) {
            return getTodayTime(time)
        }
        if (time.isThisYear()) {
            return getTime(time)
        }
        return getTimeWithYear(time)

    }

    private fun getTimeWithYear(time: Long): String {
        val formatter = SimpleDateFormat("yyyy年MM月dd日 HH:mm")//得到当前的时间
        val curDate = Date(time)
        return formatter.format(curDate)
    }

    private fun getTime(time: Long): String {
        val formatter = SimpleDateFormat("MM月dd日 HH:mm")//得到当前的时间
        val curDate = Date(time)
        return formatter.format(curDate)
    }

    fun getTodayTime(time: Long): String {
        val formatter = SimpleDateFormat("HH:mm")//得到当前的时间
        val curDate = Date(time)
        return formatter.format(curDate)
    }


    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     */
    private fun Long.isToday(): Boolean {
        val pre = Calendar.getInstance()
        val predate = Date(System.currentTimeMillis())
        pre.time = predate
        val cal = Calendar.getInstance()
        val date = Date(this)
        cal.time = date
        if (cal.get(Calendar.YEAR) == pre.get(Calendar.YEAR)) {
            val diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR)
            if (diffDay == 0) {
                return true
            }
        }
        return false
    }

    private fun Long.isThisYear(): Boolean {
        val pre = Calendar.getInstance()
        val predate = Date(System.currentTimeMillis())
        pre.time = predate
        val cal = Calendar.getInstance()
        val date = Date(this)
        cal.time = date
        if (cal.get(Calendar.YEAR) == pre.get(Calendar.YEAR)) {
            return true
        }
        return false
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     */
    fun Long.isYesterday(): Boolean {
        val pre = Calendar.getInstance()
        val predate = Date(System.currentTimeMillis())
        pre.time = predate
        val cal = Calendar.getInstance()
        val date = Date(this)
        cal.time = date
        if (cal.get(Calendar.YEAR) == pre.get(Calendar.YEAR)) {
            val diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR)
            if (diffDay == -1) {
                return true
            }
        }
        return false
    }


}