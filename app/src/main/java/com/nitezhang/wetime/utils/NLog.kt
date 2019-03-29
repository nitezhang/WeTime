package com.nitezhang.wetime.utils

import android.util.Log

object NLog {
    const val DEV = 0
    const val TEST = 1
    const val USR = 3
    var mLevel = DEV
    fun d(tag: String?, level: Int, msg: String?) {
        if(level>mLevel) {
            Log.d(tag, msg)
        }
    }
}