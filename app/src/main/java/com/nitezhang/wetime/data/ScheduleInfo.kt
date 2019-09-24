package com.nitezhang.wetime.data

import org.litepal.crud.LitePalSupport

data class ScheduleInfo(
    var content: String,
    var time: Long = System.currentTimeMillis(),
    val address: String,
    val isRemind: Boolean
) : LitePalSupport()