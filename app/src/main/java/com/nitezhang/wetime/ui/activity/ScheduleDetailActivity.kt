package com.nitezhang.wetime.ui.activity

import android.app.Activity
import android.app.AlarmManager
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import com.amap.api.location.AMapLocation
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.ScheduleInfo
import com.nitezhang.wetime.data.ScheduleInfoManager
import com.nitezhang.wetime.utils.AlarmManagerUtil
import com.nitezhang.wetime.utils.AlarmReceiver
import com.nitezhang.wetime.utils.LocationUtil
import com.nitezhang.wetime.utils.NLog
import kotlinx.android.synthetic.main.activity_schedule_detail.*
import java.util.*


class ScheduleDetailActivity : BaseActivity(), View.OnClickListener {
    private val calendar = Calendar.getInstance()
    private var mPosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_detail)
        mPosition = intent.getIntExtra("position", -1)
        toolbar_delete.setOnClickListener(this)
        toolbar_back.setOnClickListener(this)
        if (mPosition != -1) {
            ed_content.setText(ScheduleInfoManager.schedules[mPosition].content)
            calendar.timeInMillis = ScheduleInfoManager.schedules[mPosition].time
            tv_location.text = ScheduleInfoManager.schedules[mPosition].address
            cb_is_remind.isChecked = ScheduleInfoManager.schedules[mPosition].isRemind
        } else {
            calendar.timeInMillis = ScheduleInfoManager.calendar.timeInMillis
            LocationUtil.addListener(object : LocationUtil.LocationListener {
                override fun onSuccess(location: AMapLocation) {
                    tv_location.text = location.poiName
                    LocationUtil.onStop()
                    LocationUtil.onDestroy()
                }

            })
            LocationUtil.onCreate(this)
        }
        ed_content.performClick()
        setTime()
        setDate()

    }

    override fun getTitleBar(): View? {
        return schedule_detail_toolbar
    }

    private fun setTime() {
        val sb = StringBuffer()
        sb.append(
            String.format(
                "%02d:%02d",
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE)
            )
        )
        tv_time.text = sb
        //点击"时间"按钮布局 设置时间
        layout_time.setOnClickListener {
            //自定义控件
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.time_dialog, null) as LinearLayout
            val timePicker = view.findViewById(R.id.time_picker) as TimePicker
            timePicker.setIs24HourView(true)
            timePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
            timePicker.minute = Calendar.MINUTE
            //设置time布局
            builder.setView(view)
            builder.setTitle("设置时间信息")
            builder.setPositiveButton("确  定") { dialog, _ ->
                val buffer = StringBuffer()
                buffer.append(
                    String.format(
                        "%02d:%02d",
                        timePicker.hour,
                        timePicker.minute
                    )
                )
                tv_time.text = buffer
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
                calendar.set(Calendar.MINUTE, timePicker.minute)
                calendar.set(Calendar.SECOND, 0)
                dialog.cancel()
            }
            builder.setNegativeButton("取  消") { dialog, _ -> dialog.cancel() }
            builder.create().show()
        }
    }

    private fun setDate() {
        //日期格式
        val sb = StringBuffer()
        sb.append(
            String.format(
                "%d-%02d-%02d",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        )
        tv_date.text = sb
        //点击"日期"按钮布局 设置日期
        layout_date.setOnClickListener {
            //通过自定义控件AlertDialog实现
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.date_dialog, null) as LinearLayout
            val datePicker = view.findViewById(R.id.date_picker) as DatePicker
            datePicker.init(
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null
            )
            //设置date布局
            builder.setView(view)
            builder.setTitle("设置日期信息")
            builder.setPositiveButton("确  定") { dialog, _ ->
                //日期格式
                val buffer = StringBuffer()
                buffer.append(
                    String.format(
                        "%d-%02d-%02d",
                        datePicker.year,
                        datePicker.month + 1,
                        datePicker.dayOfMonth
                    )
                )
                tv_date.text = buffer
                calendar.set(Calendar.YEAR, datePicker.year)
                calendar.set(Calendar.MONTH, datePicker.month)
                calendar.set(Calendar.DAY_OF_MONTH, datePicker.dayOfMonth)
                dialog.cancel()
            }
            builder.setNegativeButton("取  消") { dialog, _ -> dialog.cancel() }
            builder.create().show()
        }
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_delete -> {
                if (mPosition != -1) {
                    ScheduleInfoManager.schedules[mPosition].delete()
                }
                finish()
            }
            R.id.toolbar_back -> {
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        val text = ed_content.text.toString()
        if (text.isNotEmpty() || mPosition != -1) {
            if (text.isEmpty() && mPosition != -1) {
                ScheduleInfoManager.schedules[mPosition].delete()
            }
            if (text.isNotEmpty()) {
                if (mPosition == -1) {
                    ScheduleInfo(
                        text,
                        calendar.timeInMillis,
                        tv_location.text.toString(),
                        cb_is_remind.isChecked
                    ).save()
                } else {
                    val schedule = ScheduleInfoManager.schedules[mPosition]
                    schedule.content = text
                    schedule.time = calendar.timeInMillis
                    schedule.save()
                }
                if (cb_is_remind.isChecked) {
                    sendAlarmBroadcast(calendar.timeInMillis, text)
                }
            }
            setResult(Activity.RESULT_OK)
        }
        super.onBackPressed()
    }

    private fun sendAlarmBroadcast(time: Long, content: String) {
        NLog.d("Alarm", "发送： $content")
        AlarmManagerUtil.sendAlarmBroadcast(
            this, time.toInt() + System.currentTimeMillis().toInt(), AlarmManager.RTC_WAKEUP,
            time, content, AlarmReceiver::class.java
        )
    }
}
