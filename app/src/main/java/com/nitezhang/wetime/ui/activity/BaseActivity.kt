package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.nitezhang.wetime.utils.NLog

abstract class BaseActivity : AppCompatActivity() {
    val TAG= this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        NLog.d(TAG, NLog.TEST, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        NLog.d(TAG, NLog.TEST, "onStart")
        super.onStart()
        initImmersionBar()
    }

    override fun onDestroy() {
        NLog.d(TAG, NLog.TEST, "onDestroy")
        super.onDestroy()
        ImmersionBar.with(this).destroy()
    }

    protected open fun initImmersionBar() {
        ImmersionBar.with(this).statusBarDarkFont(true).titleBar(getTitleBar()).init()
    }

    protected open fun getTitleBar(): View? {
        return null
    }


}