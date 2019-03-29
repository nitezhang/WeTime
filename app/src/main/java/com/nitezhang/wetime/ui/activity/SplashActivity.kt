package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import com.nitezhang.wetime.R
import java.util.*


class SplashActivity : BaseActivity() {
    companion object {
        private const val DELAY = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                MainActivity.start(this@SplashActivity)
            }
        }, DELAY)
    }
}
