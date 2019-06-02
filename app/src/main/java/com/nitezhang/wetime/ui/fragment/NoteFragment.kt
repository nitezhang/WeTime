package com.nitezhang.wetime.ui.fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gyf.barlibrary.ImmersionBar
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.ui.activity.BaseActivity
import com.nitezhang.wetime.ui.adapters.NoteAdapter
import kotlinx.android.synthetic.main.fragment_note.view.*


class NoteFragment : BaseFragment() {
    private val notes: ArrayList<NoteInfo> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private val texts = arrayListOf("","")

    override fun getLayoutId(): Int {
        return R.layout.fragment_note
    }

    override fun View.onCreateView() {
        addData()
        recyclerView = rv_note
        setPadding(0, ImmersionBar.getStatusBarHeight(activity!!), 0, 0)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = NoteAdapter(activity as BaseActivity,notes)
        tv_add.setOnClickListener {

        }
    }

    private fun addData() {
        (0 until 100).forEach {
            notes += NoteInfo(System.currentTimeMillis().toString())
        }
    }


}
