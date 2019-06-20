package com.nitezhang.wetime.data

import org.litepal.crud.LitePalSupport

data class UserInfo(var name: String, var username: String, var password: String, var isLogin: Boolean) :
    LitePalSupport()