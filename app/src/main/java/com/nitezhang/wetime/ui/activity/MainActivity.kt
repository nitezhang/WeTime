package com.nitezhang.wetime.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nitezhang.wetime.R
import com.nitezhang.wetime.ui.fragment.NoteFragment
import com.nitezhang.wetime.ui.fragment.ScheduleFragment
import com.nitezhang.wetime.ui.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), View.OnClickListener {


    companion object {
        private const val ITEM_SCHEDULE = "schedule_fragment"
        private const val ITEM_NOTE = "note_fragment"
        private const val ITEM_USER = "user_fragment"
        fun start(activity: BaseActivity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }

    }

    private var scheduleFragment: ScheduleFragment? = null
    private var noteFragment: NoteFragment? = null
    private var userFragment: UserFragment? = null
    private var mFragmentId = ITEM_USER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img_btn_schedule.setOnClickListener(this)
        img_btn_note.setOnClickListener(this)
        img_btn_user.setOnClickListener(this)
        if (savedInstanceState != null) {
            scheduleFragment =
                supportFragmentManager.getFragment(savedInstanceState, ITEM_SCHEDULE) as ScheduleFragment?
            noteFragment = supportFragmentManager.getFragment(savedInstanceState, ITEM_NOTE) as NoteFragment?
            userFragment = supportFragmentManager.getFragment(savedInstanceState, ITEM_USER) as UserFragment?
            mFragmentId = savedInstanceState.getString("fragmentId") as String
        }
        showFragment(ITEM_SCHEDULE)



    }


    private fun showFragment(fragmentId: String) {
        setMainIcon(fragmentId)
        if (fragmentId == mFragmentId) {
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        when (fragmentId) {
            ITEM_SCHEDULE -> {
                hideLastFragment(mFragmentId)
                var fragment = scheduleFragment
                if (fragment == null) {
                    fragment = ScheduleFragment()
                    transaction.add(R.id.fg_main, fragment)
                    scheduleFragment = fragment
                } else {
                    transaction.show(fragment)
                }
            }
            ITEM_NOTE -> {
                hideLastFragment(mFragmentId)
                var fragment = noteFragment
                if (fragment == null) {
                    fragment = NoteFragment()
                    transaction.add(R.id.fg_main, fragment)
                    noteFragment = fragment
                } else {
                    transaction.show(fragment)
                }
            }
            else -> {
                hideLastFragment(mFragmentId)
                var fragment = userFragment
                if (fragment == null) {
                    fragment = UserFragment()
                    transaction.add(R.id.fg_main, fragment)
                    userFragment = fragment
                } else {
                    transaction.show(fragment)
                }
            }
        }
        transaction.commit()
        mFragmentId = fragmentId

    }

    private fun hideLastFragment(fragmentId: String) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = when (fragmentId) {
            ITEM_SCHEDULE -> scheduleFragment
            ITEM_NOTE -> noteFragment
            else -> userFragment
        }
        if (fragment != null) {
            transaction.hide(fragment)
        }

        transaction.commit()

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.img_btn_schedule -> {
                showFragment(ITEM_SCHEDULE)
            }
            R.id.img_btn_note -> {
                showFragment(ITEM_NOTE)
            }
            R.id.img_btn_user -> {
                showFragment(ITEM_USER)
            }

        }
    }
//
//    private val fragmentStateAdapter = object : FragmentStateAdapter(this) {
//
//        override fun getItem(position: Int): Fragment {
//            return when (position) {
//                ITEM_SCHEDULE -> ScheduleFragment()
//                ITEM_NOTE -> NoteFragment()
//                else -> UserFragment()
//            }
//        }
//
//        override fun getItemCount(): Int {
//            return 3
//        }
//
//    }

    private fun setMainIcon(position: String) {
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

    override fun onSaveInstanceState(outState: Bundle) {
        var fragment: Fragment? = scheduleFragment
        if (fragment != null) {
            supportFragmentManager.putFragment(outState, ITEM_SCHEDULE, fragment)
        }
        fragment = noteFragment
        if (fragment != null) {
            supportFragmentManager.putFragment(outState, ITEM_NOTE, fragment)
        }
        fragment = userFragment
        if (fragment != null) {
            supportFragmentManager.putFragment(outState, ITEM_USER, fragment)
        }
        outState.putString("fragmentId", mFragmentId)
        super.onSaveInstanceState(outState)
    }
}
