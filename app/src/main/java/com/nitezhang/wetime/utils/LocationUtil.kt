package com.nitezhang.wetime.utils

import android.content.Context
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener

object LocationUtil {

    private const val TAG = "LocationUtil"
    /**
     * 声明AMapLocationClient类对象
     * 这个warning是因为静态绑定了Context导致，提示会内存泄漏
     * 解决方案：不用的时候记得调用onDestroy至空
     */
    private var mLocationClient: AMapLocationClient? = null

    private val listeners = ArrayList<LocationListener>()

    fun onCreate(context: Context, option: AMapLocationClientOption? = null) {
        var locationOption = option
        if (locationOption == null) {
            locationOption = AMapLocationClientOption()
        }

        //初始化定位
        val locationClient = AMapLocationClient(context)
        //设置定位回调监听
        locationClient.setLocationListener(mLocationListener)
        //给定位客户端对象设置定位参数
        locationClient.setLocationOption(locationOption)
        //启动定位
        locationClient.startLocation()
        mLocationClient = locationClient
    }

    fun onStop() {
        mLocationClient?.stopLocation()
        NLog.d(TAG, NLog.TEST, "onStop")
    }

    fun onDestroy() {
        mLocationClient?.onDestroy()
        mLocationClient = null
        listeners.clear()
    }

    fun setListener(listener: LocationListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: LocationListener) {
        listeners.remove(listener)
    }

    //声明定位回调监听器
    private var mLocationListener = AMapLocationListener {
        if (it != null) {
            if (it.errorCode == 0) {
                //可在其中解析amapLocation获取相应内容。
                NLog.d(TAG, "success! address: ${it.address}, latitude: ${it.latitude}, longitude: ${it.longitude}")
                for (listener in listeners) {
                    listener.onSuccess(it)
                }
            } else {
                for (listener in listeners) {
                    listener.onError(it)
                }
            }
        }
    }

    interface LocationListener {
        fun onSuccess(location: AMapLocation)
        fun onError(location: AMapLocation) {
            //定位失败时，可通过errCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
            NLog.e(TAG, "error! errCode: ${location.errorCode}, errInfo: ${location.errorInfo}")
        }
    }
}