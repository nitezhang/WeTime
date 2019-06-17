package com.nitezhang.wetime.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.ui.activity.BaseActivity
import com.nitezhang.wetime.ui.activity.NoteDetailActivity
import com.nitezhang.wetime.ui.activity.NoteRecycleDetailActivity
import com.nitezhang.wetime.ui.fragment.BaseFragment
import com.nitezhang.wetime.utils.TimeUtil
import kotlinx.android.synthetic.main.item_note.view.*


class NoteRecycleAdapter(val activity: BaseActivity, var notes: List<NoteInfo>) :
    RecyclerView.Adapter<NoteRecycleAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = notes[position].content
        holder.time.text = TimeUtil.getNoteTime(notes[position].time)
        holder.itemView.setOnClickListener {
            activity.startActivityForResult(Intent(activity, NoteRecycleDetailActivity::class.java).apply { putExtra("position", position) }
                    .apply { putExtra("from", "recycle") }, 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_note_recycle, parent, false)

        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content: TextView = itemView.tv_content
        var time: TextView = itemView.tv_time
    }
}