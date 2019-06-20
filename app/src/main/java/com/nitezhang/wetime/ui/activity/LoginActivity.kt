package com.nitezhang.wetime.ui.activity

import android.os.Bundle
import android.view.View
import com.nitezhang.wetime.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        toolbar_back.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_back -> {
                onBackPressed()
            }
        }
    }
}
