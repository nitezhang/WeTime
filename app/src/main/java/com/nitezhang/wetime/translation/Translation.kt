package com.nitezhang.wetime.translation

data class Translation(
    var type: String,
    var errorCode: Int,
    var elapsedTime: Int,
    var translateResult: MutableList<MutableList<TranslateResultBean>>
) {

    data class TranslateResultBean(
        var src: String,
        var tgt: String
    )
}
