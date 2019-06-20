package com.nitezhang.wetime.ui.fragment

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.UserInfo
import com.nitezhang.wetime.data.UserInfoManager
import com.nitezhang.wetime.ui.activity.LoginActivity
import com.nitezhang.wetime.ui.activity.NoteRecycleActivity
import com.nitezhang.wetime.ui.activity.TomatoTimeActivity
import kotlinx.android.synthetic.main.fragment_user.view.*
import org.litepal.LitePal
import org.litepal.extension.findAll


class UserFragment : BaseFragment(), View.OnClickListener {
    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun View.onCreateView() {
        layout_note_recycle.setOnClickListener(this@UserFragment)
        layout_tomato_time.setOnClickListener(this@UserFragment)
        cv_user.setOnClickListener(this@UserFragment)
        tvName = tv_name
        tvUsername = tv_username
        getData()
    }

    private fun getData() {
        UserInfoManager.users = ArrayList()
        val data = LitePal.findAll<UserInfo>()
        UserInfoManager.users = data
        for (user in UserInfoManager.users) {
            if (user.isLogin) {
                tvName.text = user.name
                tvUsername.text = user.username
                break
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cv_user -> {
                startActivityForResult(Intent(context, LoginActivity::class.java), 1)
            }
            R.id.layout_note_recycle -> {
                startActivity(Intent(context, NoteRecycleActivity::class.java))
            }
            R.id.layout_tomato_time -> {
                startActivity(Intent(context, TomatoTimeActivity::class.java))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getData()
    }
}
