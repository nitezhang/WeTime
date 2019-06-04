package com.nitezhang.wetime.data

import org.litepal.crud.LitePalSupport
import java.io.Serializable

data class NoteInfo(var content: String, var time: Long = System.currentTimeMillis()) : LitePalSupport(), Serializable