package com.nitezhang.wetime.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nitezhang.wetime.R
import com.nitezhang.wetime.data.UserInfo
import com.nitezhang.wetime.data.UserInfoManager
import kotlinx.android.synthetic.main.activity_login.*
import org.litepal.LitePal
import org.litepal.extension.findAll

class LoginActivity : BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        toolbar_back.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_back -> {
                onBackPressed()
            }
            R.id.btn_login -> {
                var flag = true
                for (user in UserInfoManager.users) {
                    if (user.username == et_username.text.toString() && user.password == et_password.text.toString()) {
                        user.isLogin = true
                        getData()
                        user.save()
                        flag = false
                        finish()
                    }
                }
                if (flag) {
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.tv_register -> {
                startActivityForResult(Intent(this, RegisterActivity::class.java), 1)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            finish()
        }
    }
}
