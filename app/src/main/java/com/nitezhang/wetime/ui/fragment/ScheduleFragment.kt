package com.nitezhang.wetime.ui.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.data.ScheduleInfo
import com.nitezhang.wetime.ui.activity.BaseActivity
import com.nitezhang.wetime.ui.activity.NoteDetailActivity
import com.nitezhang.wetime.ui.activity.ScheduleDetailActivity
import com.nitezhang.wetime.ui.adapters.NoteAdapter
import com.nitezhang.wetime.ui.adapters.ScheduleAdapter
import kotlinx.android.synthetic.main.fragment_note.view.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import kotlinx.android.synthetic.main.fragment_schedule.view.tv_add
import org.litepal.LitePal
import org.litepal.extension.findAll


class ScheduleFragment : BaseFragment() {


    private lateinit var schedules: List<ScheduleInfo>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_schedule
    }

    override fun View.onCreateView() {
        getData()
        recyclerView = rv_schedule
//        setPadding(0, ImmersionBar.getStatusBarHeight(activity!!), 0, 0)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ScheduleAdapter(activity as BaseActivity, this@ScheduleFragment, schedules)
        recyclerView.adapter = adapter
        tv_add.setOnClickListener {
            startActivityForResult(Intent(activity, ScheduleDetailActivity::class.java), 1)
        }
    }

    private fun getData() {
        schedules = LitePal.findAll<ScheduleInfo>()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 3) {
            schedules[requestCode].delete()
        }
        if (resultCode == 2) {
            val content = data!!.getStringExtra("content")
            val schedule = schedules[requestCode]
            schedule.content = content
            schedule.time = System.currentTimeMillis()
            schedule.save()
        }
        getData()
        adapter.schedules = schedules
        adapter.notifyDataSetChanged()
    }

}
