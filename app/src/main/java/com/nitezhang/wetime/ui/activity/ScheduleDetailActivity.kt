package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.ScheduleInfo
import kotlinx.android.synthetic.main.activity_schedule_detail.*
import java.util.*


class ScheduleDetailActivity : BaseActivity(), View.OnClickListener {
    private var mSchedule: ScheduleInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_detail)
        val schedule = intent.getSerializableExtra("schedule") as ScheduleInfo?
        toolbar_delete.setOnClickListener(this)
        toolbar_back.setOnClickListener(this)
        if (schedule != null) {
            ed_content.setText(schedule.content)
            mSchedule = schedule
        }
        ed_content.performClick()
        setTime()
        setDate()
    }

    override fun getTitleBar(): View? {
        return schedule_detail_toolbar
    }

    private fun setTime() {
        //点击"时间"按钮布局 设置时间
        layout_time.setOnClickListener {
            //自定义控件
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.time_dialog, null) as LinearLayout
            val timePicker = view.findViewById(R.id.time_picker) as TimePicker
            //初始化时间
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            timePicker.setIs24HourView(true)
            timePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
            timePicker.minute = Calendar.MINUTE
            //设置time布局
            builder.setView(view)
            builder.setTitle("设置时间信息")
            builder.setPositiveButton("确  定") { dialog, _ ->
                val mHour = timePicker.hour
                val mMinute = timePicker.minute
                //时间小于10的数字 前面补0 如01:12:00
                tv_time.text = StringBuilder().append(if (mHour < 10) "0$mHour" else mHour).append(":")
                    .append(if (mMinute < 10) "0$mMinute" else mMinute).append(":00")
                dialog.cancel()
            }
            builder.setNegativeButton("取  消") { dialog, _ -> dialog.cancel() }
            builder.create().show()
        }
    }

    private fun setDate() {
        //点击"日期"按钮布局 设置日期
        layout_date.setOnClickListener {
            //通过自定义控件AlertDialog实现
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.date_dialog, null) as LinearLayout
            val datePicker = view.findViewById(R.id.date_picker) as DatePicker
            //设置日期简略显示 否则详细显示 包括:星期\周
//            datePicker.calendarViewShown = false
            //初始化当前日期
            val calendar = Calendar.getInstance();
            calendar.timeInMillis = System.currentTimeMillis()
            datePicker.init(
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null
            )
            //设置date布局
            builder.setView(view)
            builder.setTitle("设置日期信息")
            builder.setPositiveButton("确  定") { dialog, _ ->
                //日期格式
                val sb = StringBuffer()
                sb.append(
                    String.format(
                        "%d-%02d-%02d",
                        datePicker.year,
                        datePicker.month + 1,
                        datePicker.dayOfMonth
                    )
                )
                tv_date.text = sb
                //赋值后面闹钟使用
                val mYear = datePicker.year
                val mMonth = datePicker.month
                val mDay = datePicker.dayOfMonth
                dialog.cancel()
            }
            builder.setNegativeButton("取  消") { dialog, _ -> dialog.cancel() }
            builder.create().show()
        }
    }


    override fun onClick(v: View) {
        val schedule = mSchedule
        when (v.id) {
            R.id.toolbar_delete -> {
                if (schedule != null) {
                    setResult(3, intent.apply { putExtra("schedule", schedule) })
                }
                finish()
            }
            R.id.toolbar_back -> {
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        val schedule = mSchedule
        val text = ed_content.text.toString()
        if (text.isNotEmpty() || schedule != null) {
            if (text.isEmpty() && schedule != null) {
                setResult(3)
            }
            if (text.isNotEmpty()) {
                if (schedule == null) {
                    ScheduleInfo(text).save()
                } else {
                    setResult(2, intent.apply { putExtra("content", text) })
                }
            }
        }
        super.onBackPressed()
    }
}
