package com.nitezhang.wetime.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitezhang.wetime.R

class TestAdapter(context: Context) : BaseAdapter(context) {
    override fun onCreateContentViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.toolbar_layout, parent, false)
        return ContentViewHolder(layout)
    }

    override fun getContentItemCount(): Int {
        return 30
    }

    override fun onBindContentHolderView(holder: RecyclerView.ViewHolder, position: Int) {

    }

}