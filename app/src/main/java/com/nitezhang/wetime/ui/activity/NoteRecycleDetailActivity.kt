package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import android.view.View
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import com.nitezhang.wetime.data.NoteRecycleInfoManager
import kotlinx.android.synthetic.main.activity_note_recycle_detail.*

class NoteRecycleDetailActivity : BaseActivity(), View.OnClickListener {
    private var mPosition = -1
    private lateinit var from: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_recycle_detail)
        mPosition = intent.getIntExtra("position", -1)
        from = intent.getStringExtra("from") as String
        toolbar_restore.setOnClickListener(this)
        toolbar_back.setOnClickListener(this)

        if (mPosition != -1) {
            ed_content.setText(NoteRecycleInfoManager.notes[mPosition].content)
        }
        ed_content.performClick()
    }

    override fun getTitleBar(): View? {
        return note_detail_toolbar
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_restore -> {
                if (mPosition != -1) {
                    val note = NoteRecycleInfoManager.notes[mPosition]
                    note.content = ed_content.text.toString()
                    note.time = System.currentTimeMillis()
                    note.isDelete = false
                    note.save()
                }
                finish()
            }
            R.id.toolbar_back -> {
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        val text = ed_content.text.toString()
        if (text.isNotEmpty() || mPosition != -1) {
            if (text.isEmpty() && mPosition != -1) {
                NoteRecycleInfoManager.notes[mPosition].delete()
            }
            if (text.isNotEmpty()) {
                if (mPosition == -1) {
                    NoteInfo(text).save()
                } else {
                    val note = NoteRecycleInfoManager.notes[mPosition]
                    note.content = text
                    note.time = System.currentTimeMillis()
                    note.save()
                }
            }
        }
        super.onBackPressed()
    }
}
