package com.nitezhang.wetime.ui.fragment

import android.content.Intent
import android.view.View
import com.nitezhang.wetime.R
import com.nitezhang.wetime.ui.activity.NoteRecycleActivity
import kotlinx.android.synthetic.main.fragment_user.view.*


class UserFragment : BaseFragment(), View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun View.onCreateView() {
        layout_note_recycle.setOnClickListener(this@UserFragment)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.layout_note_recycle -> {
                startActivity(Intent(context, NoteRecycleActivity::class.java))
            }
        }
    }
}
