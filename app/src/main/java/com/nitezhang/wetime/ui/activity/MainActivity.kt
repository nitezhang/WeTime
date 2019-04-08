package com.nitezhang.wetime.ui.activity

import android.content.Intent
import android.os.Bundle
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClientOption
import com.nitezhang.wetime.R
import com.nitezhang.wetime.utils.LocationUtil
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
        LocationUtil.onCreate(applicationContext,AMapLocationClientOption().apply { isOnceLocationLatest = true })
        LocationUtil.setListener(object:LocationUtil.LocationListener{
            override fun onSuccess(location: AMapLocation) {
                tv_content.text = location.address
            }

        })
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationUtil.onDestroy()
    }
}
