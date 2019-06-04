package com.nitezhang.wetime.ui.fragment

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyf.barlibrary.ImmersionBar
import com.nitezhang.wetime.R
import com.nitezhang.wetime.ui.adapters.TestAdapter
import com.nitezhang.wetime.utils.MapUtil
import kotlinx.android.synthetic.main.fragment_user.view.*
import kotlinx.android.synthetic.main.layout_map.view.*


class UserFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView

    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun View.onCreateView() {



    }

}
