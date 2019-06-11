package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import android.view.View
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.NoteInfo
import kotlinx.android.synthetic.main.activity_note_detail.*

class NoteDetailActivity : BaseActivity(), View.OnClickListener {
    private var mNote: NoteInfo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        val note = intent.getSerializableExtra("note") as NoteInfo?
        toolbar_delete.setOnClickListener(this)
        toolbar_back.setOnClickListener(this)
        if (note != null) {
            ed_content.setText(note.content)
            mNote = note
        }
        ed_content.performClick()
    }

    override fun getTitleBar(): View? {
        return note_detail_toolbar
    }

    override fun onClick(v: View) {
        val note = mNote
        when (v.id) {
            R.id.toolbar_delete -> {
                if (note != null) {
                    setResult(3)
                }
                finish()
            }
            R.id.toolbar_back -> {
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        val note = mNote
        val text = ed_content.text.toString()
        if (text.isNotEmpty() || note != null) {
            if (text.isEmpty() && note != null) {
                setResult(3)
            }
            if (text.isNotEmpty()) {
                if (note == null) {
                    NoteInfo(text).save()
                } else {
                    setResult(2, intent.apply { putExtra("content", text) })
                }
            }
        }
        super.onBackPressed()
    }
}
