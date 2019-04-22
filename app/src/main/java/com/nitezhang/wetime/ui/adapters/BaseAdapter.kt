package com.nitezhang.wetime.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_NORMAL = 1
    }

    var mHeaderView: View? = null
    private var mRecyclerView: RecyclerView? = null

    abstract fun onCreateContentViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    abstract fun getContentItemCount(): Int
    abstract fun onBindContentHolderView(holder: RecyclerView.ViewHolder, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mHeaderView?.let {
            if (viewType == TYPE_HEADER) return HeaderViewHolder(it)
        }
        return onCreateContentViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return if (mHeaderView != null) getContentItemCount() + 1 else getContentItemCount()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_HEADER) return
        onBindContentHolderView(holder, getContentPosition(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && mHeaderView != null) TYPE_HEADER else TYPE_NORMAL
    }

    private fun getContentPosition(position: Int): Int {
        return if (mHeaderView != null) position - 1 else position
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}