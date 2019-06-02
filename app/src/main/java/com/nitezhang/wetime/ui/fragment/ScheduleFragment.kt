package com.nitezhang.wetime.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.ui.activity.BaseActivity
import com.nitezhang.wetime.ui.adapters.ScheduleAdapter
import kotlinx.android.synthetic.main.fragment_schedule.view.*


class ScheduleFragment : BaseFragment() {


    private val schedules: ArrayList<NoteInfo> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private val texts = arrayListOf("", "")

    override fun getLayoutId(): Int {
        return R.layout.fragment_schedule
    }

    override fun View.onCreateView() {
        addData()
        recyclerView = rv_schedule
//        setPadding(0, ImmersionBar.getStatusBarHeight(activity!!), 0, 0)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ScheduleAdapter(activity as BaseActivity, schedules)
        tv_add.setOnClickListener {

        }
    }

    private fun addData() {
        (0 until 100).forEach {
            schedules += NoteInfo(System.currentTimeMillis().toString())
        }
    }

}
