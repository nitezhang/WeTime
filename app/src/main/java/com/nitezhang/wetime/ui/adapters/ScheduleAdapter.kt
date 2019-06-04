package com.nitezhang.wetime.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.data.ScheduleInfo
import com.nitezhang.wetime.ui.activity.BaseActivity
import com.nitezhang.wetime.ui.activity.NoteDetailActivity
import com.nitezhang.wetime.ui.activity.ScheduleDetailActivity
import com.nitezhang.wetime.ui.fragment.BaseFragment
import com.nitezhang.wetime.utils.TimeUtil
import kotlinx.android.synthetic.main.item_note.view.*


class ScheduleAdapter(val activity: BaseActivity, val fragment: BaseFragment, var schedules: List<ScheduleInfo>) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = schedules[position].content
        holder.time.text = TimeUtil.getNoteTime(schedules[position].time)
        holder.itemView.setOnClickListener {
            fragment.startActivityForResult(Intent(activity, ScheduleDetailActivity::class.java).apply {
                putExtra("schedule", schedules[position])
            }, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content: TextView = itemView.tv_content
        var time: TextView = itemView.tv_time
    }
}