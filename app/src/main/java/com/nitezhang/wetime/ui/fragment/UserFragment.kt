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

    override fun onCreateView(view: View) {
        view.rv_test.layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
        val adapter = TestAdapter(context!!)
        recyclerView = view.rv_test
        recyclerView.adapter = adapter
        val layout = LayoutInflater.from(context).inflate(R.layout.layout_map, recyclerView, false)
        adapter.mHeaderView = layout
        view.setPadding(0, ImmersionBar.getStatusBarHeight(activity!!), 0, 0)
        layout.map_view.onCreate(null)
        val map = layout.map_view.map
        MapUtil.setAMap(map)


    }

}
