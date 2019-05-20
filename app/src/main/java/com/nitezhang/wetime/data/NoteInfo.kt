package com.nitezhang.wetime.data

data class NoteInfo(var content: String, var time: Long = System.currentTimeMillis())