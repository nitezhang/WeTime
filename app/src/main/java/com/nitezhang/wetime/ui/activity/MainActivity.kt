package com.nitezhang.wetime.ui.activity

import android.content.Intent
import android.os.Bundle
import com.nitezhang.wetime.R
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : BaseTitleActivity() {

    companion object {
        fun start(activity: BaseActivity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar_title.text = "首页"
        toolbar_menu.text = "编辑"
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }
}
