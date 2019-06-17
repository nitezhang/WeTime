package com.nitezhang.wetime.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.data.NoteRecycleInfoManager
import com.nitezhang.wetime.ui.adapters.NoteRecycleAdapter
import kotlinx.android.synthetic.main.activity_note_recycle.*
import org.litepal.LitePal
import org.litepal.extension.findAll

class NoteRecycleActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteRecycleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_recycle)
        recyclerView = rv_note
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NoteRecycleAdapter(this, NoteRecycleInfoManager.notes)
        recyclerView.adapter = adapter
        getData()
    }

    private fun getData() {
        NoteRecycleInfoManager.notes = ArrayList()
        val data = LitePal.findAll<NoteInfo>()
        data.sortBy { it.time }
        for (note in data.reversed()) {
            if (note.isDelete) {
                NoteRecycleInfoManager.notes += note
            }
        }
        adapter.notes = NoteRecycleInfoManager.notes
        adapter.notifyDataSetChanged()
    }

    override fun getTitleBar(): View? {
        return note_toolbar
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getData()
    }
}
