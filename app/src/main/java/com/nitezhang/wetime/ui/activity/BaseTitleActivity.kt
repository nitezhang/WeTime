package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.toolbar_layout.*


abstract class BaseTitleActivity : BaseActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setBackIcon()
    }

    private fun setBackIcon() {
        if (isShowBack()) {
            toolbar_back.setOnClickListener { onBackPressed() }
            toolbar_back.visibility = View.VISIBLE
        }
    }

    protected abstract fun getContentView(): Int

    protected open fun isShowBack(): Boolean {
        return true
    }

    override fun getTitleBar(): View? {
        return toolbar
    }

}