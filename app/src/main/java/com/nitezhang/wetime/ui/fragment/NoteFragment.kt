package com.nitezhang.wetime.ui.fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gyf.barlibrary.ImmersionBar
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.ui.adapters.NoteAdapter
import kotlinx.android.synthetic.main.fragment_note.view.*


class NoteFragment : BaseFragment() {
    private val notes: ArrayList<NoteInfo> = ArrayList()
    private lateinit var recyclerView: RecyclerView

    override fun getLayoutId(): Int {
        return R.layout.fragment_note
    }

    override fun onCreateView(view: View) {
        recyclerView = view.rv_note
        view.setPadding(0, ImmersionBar.getStatusBarHeight(activity!!), 0, 0)
        (0 until 100).forEach {
            notes += NoteInfo(it.toString())
        }
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = NoteAdapter(notes)
    }


}
