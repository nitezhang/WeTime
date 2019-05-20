package com.nitezhang.wetime.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.utils.TimeUtil
import kotlinx.android.synthetic.main.item_note.view.*


class NoteAdapter(private val notes: ArrayList<NoteInfo>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = notes[position].content
        if (position == 0) {
            holder.content.text =
                "${notes[position].content}\n${notes[position].content}\n${notes[position].content}\n${notes[position].content}\n${notes[position].content}\n${notes[position].content}\n"
        } else if (position == 4) {
            holder.content.text = "${notes[position].content}\n${notes[position].content}"
        }
        holder.time.text = TimeUtil.getNoteTime(notes[position].time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

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