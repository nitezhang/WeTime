package com.nitezhang.wetime.ui.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.nitezhang.wetime.R
import java.util.*


class SplashActivity : BaseActivity() {
    companion object {
        private const val DELAY = 1000L
        private const val PERMISSION_REQUEST_CODE = 1001
        private val permissions =
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getPermissions()
        Timer().schedule(object : TimerTask() {
            override fun run() {
                MainActivity.start(this@SplashActivity)
            }
        }, DELAY)
    }

    private fun getPermissions() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
        }
    }
}
