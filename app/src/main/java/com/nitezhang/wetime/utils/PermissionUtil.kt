package com.nitezhang.wetime.utils

import android.Manifest
import android.os.Build
import androidx.core.app.ActivityCompat
import com.nitezhang.wetime.ui.activity.BaseActivity

object PermissionUtil {
    private val permissions =
        arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
        )

    fun getPermissions(activity: BaseActivity, requestCode: Int) {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }
    }
}