package com.nitezhang.wetime.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.nitezhang.wetime.R
import com.nitezhang.wetime.ui.fragment.NoteFragment
import com.nitezhang.wetime.ui.fragment.ScheduleFragment
import com.nitezhang.wetime.ui.fragment.UserFragment
import com.nitezhang.wetime.utils.NLog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), View.OnClickListener {


    companion object {
        private const val ITEM_SCHEDULE = 0
        private const val ITEM_NOTE = 1
        private const val ITEM_USER = 2
        fun start(activity: BaseActivity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img_btn_schedule.setOnClickListener(this)
        img_btn_note.setOnClickListener(this)
        img_btn_user.setOnClickListener(this)
        vp_main.adapter = fragmentStateAdapter
        vp_main.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                setMainIcon(position + if (positionOffsetPixels > 500) 1 else 0)
            }
        })
        setMainIcon(ITEM_SCHEDULE)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.img_btn_schedule -> {
                vp_main.currentItem = ITEM_SCHEDULE
                setMainIcon(ITEM_SCHEDULE)
            }
            R.id.img_btn_note -> {
                vp_main.currentItem = ITEM_NOTE
                setMainIcon(ITEM_NOTE)
            }
            R.id.img_btn_user -> {
                vp_main.currentItem = ITEM_USER
                setMainIcon(ITEM_USER)
            }

        }
    }

    private val fragmentStateAdapter = object : FragmentStateAdapter(this) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                ITEM_SCHEDULE -> ScheduleFragment()
                ITEM_NOTE -> NoteFragment()
                else -> UserFragment()
            }
        }

        override fun getItemCount(): Int {
            return 3
        }

    }

    private fun setMainIcon(position: Int) {
        NLog.d(TAG, "setMainIcon: position = $position")
        img_btn_schedule.setImageResource(R.drawable.icon_main_schedule)
        img_btn_note.setImageResource(R.drawable.icon_main_note)
        img_btn_user.setImageResource(R.drawable.icon_main_user)
        return when (position) {
            ITEM_SCHEDULE -> img_btn_schedule.setImageResource(R.drawable.icon_main_schedule_selected)
            ITEM_NOTE -> img_btn_note.setImageResource(R.drawable.icon_main_note_selected)
            else -> img_btn_user.setImageResource(R.drawable.icon_main_user_selected)
        }
    }

    private var backTime = 0L
    override fun onBackPressed() {
        val nowTime = SystemClock.uptimeMillis()
        if (nowTime - backTime < 2000) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show()
            backTime = nowTime
        }
    }
}
