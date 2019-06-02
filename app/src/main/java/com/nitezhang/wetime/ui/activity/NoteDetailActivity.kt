package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import android.view.View
import com.nitezhang.wetime.R
import kotlinx.android.synthetic.main.fragment_note.*

class NoteDetailActivity : BaseTitleActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_note_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar_title.text = "随笔"
    }

    override fun getTitleBar(): View? {
        return super.getTitleBar()
    }
}
