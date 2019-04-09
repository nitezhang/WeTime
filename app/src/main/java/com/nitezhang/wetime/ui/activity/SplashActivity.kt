package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import com.nitezhang.wetime.R
import com.nitezhang.wetime.utils.PermissionUtil
import java.util.*


class SplashActivity : BaseActivity() {
    companion object {
        private const val DELAY = 1000L
        private const val PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        PermissionUtil.getPermissions(this, PERMISSION_REQUEST_CODE)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                MainActivity.start(this@SplashActivity)
            }
        }, DELAY)
    }
}
