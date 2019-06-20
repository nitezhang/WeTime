package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import android.view.View
import com.nitezhang.wetime.R
import kotlinx.android.synthetic.main.activity_tomato_time.*
import java.util.*


class TomatoTimeActivity : BaseActivity(), View.OnClickListener {


    private var mTimer: Timer? = null
    private var time = 25 * 60
    private var isWorking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomato_time)
        btn_start.setOnClickListener(this)
        toolbar_back.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_back -> {
                onBackPressed()
            }
            R.id.btn_start -> {
                if (!isWorking) {
                    time = 25 * 60
                    val timer = Timer()
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            runOnUiThread {
                                time--
                                if (time < 0) {
                                    timer.cancel()
                                    btn_start.text = "开始番茄时间"
                                    isWorking = false
                                } else {
                                    tv_title.text = "${time / 60}:${time % 60}"
                                }
                            }
                        }
                    }, 0, 1000)
                    mTimer = timer
                    btn_start.text = "放弃番茄时间"
                    isWorking = true
                } else {
                    tv_title.text = "25:00"
                    mTimer?.cancel()
                    btn_start.text = "开始番茄时间"
                    isWorking = false
                }

            }
        }
    }
}