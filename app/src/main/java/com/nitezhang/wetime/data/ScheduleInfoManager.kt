package com.nitezhang.wetime.data

import java.util.*
import kotlin.collections.ArrayList

object ScheduleInfoManager {
    var schedules: List<ScheduleInfo> = ArrayList()
    var calendar = Calendar.getInstance()
}
