package com.nitezhang.wetime.ui.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyf.immersionbar.ImmersionBar
import com.necer.entity.NDate
import com.necer.listener.OnCalendarChangedListener
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.ScheduleInfo
import com.nitezhang.wetime.data.ScheduleInfoManager
import com.nitezhang.wetime.ui.activity.BaseActivity
import com.nitezhang.wetime.ui.activity.ScheduleDetailActivity
import com.nitezhang.wetime.ui.adapters.ScheduleAdapter
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import org.litepal.LitePal
import org.litepal.extension.findAll
import java.util.*
import kotlin.collections.ArrayList


class ScheduleFragment : BaseFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_schedule
    }

    override fun View.onCreateView() {
        ImmersionBar.with(this@ScheduleFragment).statusBarDarkFont(false).init()
        recyclerView = rv_schedule
//        setPadding(0, ImmersionBar.getStatusBarHeight(activity!!), 0, 0)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ScheduleAdapter(activity as BaseActivity, this@ScheduleFragment, ScheduleInfoManager.schedules)
        recyclerView.adapter = adapter
        tv_add.setOnClickListener {
            startActivityForResult(Intent(activity, ScheduleDetailActivity::class.java), 1)
        }
        miui9Calendar.setOnCalendarChangedListener(object : OnCalendarChangedListener {

            override fun onCalendarDateChanged(date: NDate, isClick: Boolean) {
                //日历回调 NDate包含公历、农历、节气、节假日、闰年等信息
                ScheduleInfoManager.calendar.timeInMillis = System.currentTimeMillis()
                ScheduleInfoManager.calendar.set(Calendar.YEAR, date.localDate.year)
                ScheduleInfoManager.calendar.set(Calendar.MONTH, date.localDate.monthOfYear - 1)
                ScheduleInfoManager.calendar.set(Calendar.DAY_OF_MONTH, date.localDate.dayOfMonth)
                tv_month.text = "${date.localDate.monthOfYear}月"
                tv_year.text = "${date.localDate.year}年"
                getData()
            }

            override fun onCalendarStateChanged(isMonthSate: Boolean) {
                //日历状态回调， 月->周 isMonthSate返回false ，反之返回true
            }
        })
    }

    private fun getData() {
        val schedules = LitePal.findAll<ScheduleInfo>()
        val mSchedules = ArrayList<ScheduleInfo>()
        for (schedule in schedules) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = schedule.time
            if (calendar.get(Calendar.DAY_OF_YEAR) == ScheduleInfoManager.calendar.get(Calendar.DAY_OF_YEAR)) {
                mSchedules += schedule
            }
        }
        mSchedules.sortBy { it.time }
        ScheduleInfoManager.schedules = mSchedules
        adapter.schedules = ScheduleInfoManager.schedules
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getData()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            ImmersionBar.with(this).statusBarDarkFont(false).init()
        }
    }

}
