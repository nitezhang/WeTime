package com.nitezhang.wetime.data

import org.litepal.crud.LitePalSupport

data class NoteInfo(var content: String, var time: Long = System.currentTimeMillis(), var isDelete: Boolean = false) :
    LitePalSupport()