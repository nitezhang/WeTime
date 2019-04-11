package com.nitezhang.wetime.utils

import android.graphics.Color
import com.amap.api.maps.AMap
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.MyLocationStyle

object MapUtil {
    fun setAMap(map: AMap) {
        map.myLocationStyle = MyLocationStyle()
            .apply { myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE) }
            .apply { strokeWidth(0f) }
            .apply { radiusFillColor(Color.TRANSPARENT) }
        map.isMyLocationEnabled = true
        map.minZoomLevel = 15f
        setUiSettings(map.uiSettings)
    }

    private fun setUiSettings(uiSettings: UiSettings) {
        uiSettings.isZoomControlsEnabled = false
        uiSettings.isRotateGesturesEnabled = false
        uiSettings.isTiltGesturesEnabled = false
    }
}