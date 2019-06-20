package com.nitezhang.wetime.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.UserInfo
import com.nitezhang.wetime.data.UserInfoManager
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_user.*
import org.litepal.LitePal
import org.litepal.extension.findAll

class RegisterActivity : BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        toolbar_back.setOnClickListener(this)
        btn_register.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_back -> {
                onBackPressed()
            }
            R.id.btn_register -> {
                val user =
                    UserInfo(et_name.text.toString(), et_username.text.toString(), et_password.text.toString(), true)
                getData()
                user.save()
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    private fun getData() {
        UserInfoManager.users = ArrayList()
        val data = LitePal.findAll<UserInfo>()
        UserInfoManager.users = data
        for (user in UserInfoManager.users) {
            user.isLogin = false
            user.save()
        }
    }
}
