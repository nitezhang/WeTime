package com.nitezhang.wetime.ui.activity

import android.content.Intent
import android.os.Bundle
import com.nitezhang.wetime.R
import com.nitezhang.wetime.utils.MapUtil
import kotlinx.android.synthetic.main.activity_main.*
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
        map_view.onCreate(savedInstanceState)
        val map = map_view.map
        MapUtil.setAMap(map)

    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        super.onDestroy()
        map_view.onDestroy()
    }
}
