package com.nitezhang.wetime.ui.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gyf.barlibrary.ImmersionBar
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.data.NoteInfoManager
import com.nitezhang.wetime.ui.activity.BaseActivity
import com.nitezhang.wetime.ui.activity.NoteDetailActivity
import com.nitezhang.wetime.ui.adapters.NoteAdapter
import kotlinx.android.synthetic.main.fragment_note.view.*
import org.litepal.LitePal
import org.litepal.extension.findAll


class NoteFragment : BaseFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_note
    }

    override fun View.onCreateView() {
        getData()
        recyclerView = rv_note
        setPadding(0, ImmersionBar.getStatusBarHeight(activity!!), 0, 0)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = NoteAdapter(activity as BaseActivity, this@NoteFragment, NoteInfoManager.notes)
        recyclerView.adapter = adapter
        tv_add.setOnClickListener {
            startActivityForResult(Intent(activity, NoteDetailActivity::class.java), 1)
        }
    }

    private fun getData() {
        val data = LitePal.findAll<NoteInfo>()
        NoteInfoManager.notes = data.reversed()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 3) {
            NoteInfoManager.notes[requestCode].delete()
        }
        if (resultCode == 2) {
            val content = data!!.getStringExtra("content")
            val note = NoteInfoManager.notes[requestCode]
            note.content = content
            note.time = System.currentTimeMillis()
            note.save()
        }
        getData()
        adapter.notes = NoteInfoManager.notes
        adapter.notifyDataSetChanged()
    }


}
