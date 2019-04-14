package com.nitezhang.wetime.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nitezhang.wetime.utils.NLog


abstract class BaseFragment : Fragment() {

    val TAG = this.javaClass.name

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        NLog.d(TAG, NLog.TEST, "onCreateView: layoutId = ${getLayoutId()}")
        val view = inflater.inflate(getLayoutId(), container, false)
        onCreateView(view)
        return view
    }

    abstract fun onCreateView(view: View)

    abstract fun getLayoutId(): Int

}
