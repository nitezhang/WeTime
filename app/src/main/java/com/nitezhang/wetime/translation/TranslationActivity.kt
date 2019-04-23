package com.nitezhang.wetime.translation

import android.os.Bundle
import com.nitezhang.wetime.R
import com.nitezhang.wetime.ui.activity.BaseActivity
import com.nitezhang.wetime.utils.NLog
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observer


class TranslationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        request()
        // 使用Retrofit封装的方法
    }

    private fun request() {
        Retrofit.Builder()
            .baseUrl("https://fanyi.youdao.com/") // 设置 网络请求 Url
            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
            .build()
            .create<TranslationInterface>(TranslationInterface::class.java)//创建 网络请求接口 的实例
            .getObservable("I love you")//采用Observable<...>形式 对 网络请求 进行封装
            .subscribeOn(Schedulers.io())               // 在IO线程进行网络请求
            .observeOn(AndroidSchedulers.mainThread())  // 回到主线程 处理请求结果
            .subscribe(object : Observer<Translation> {
                override fun onSubscribe(d: Disposable) {
                    NLog.d(TAG, "开始采用subscribe连接")
                }

                override fun onNext(result: Translation) {
                    // 步骤8：对返回的数据进行处理
                    NLog.d(TAG, result.translateResult[0][0].tgt)
                }

                override fun onError(e: Throwable) {
                    NLog.d(TAG, "请求失败")
                }

                override fun onComplete() {
                    NLog.d(TAG, "请求成功")
                }
            })
//        //步骤4：创建Retrofit对象
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://fanyi.youdao.com/") // 设置 网络请求 Url
//            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
//            .build()
//
//        // 步骤5：创建 网络请求接口 的实例
//        val request = retrofit.create<TranslationInterface>(TranslationInterface::class.java)
//
//        // 步骤6：采用Observable<...>形式 对 网络请求 进行封装
//        val observable = request.getCall("I love you")
//
//        // 步骤7：发送网络请求
//        observable.subscribeOn(Schedulers.io())               // 在IO线程进行网络请求
//            .observeOn(AndroidSchedulers.mainThread())  // 回到主线程 处理请求结果
//            .subscribe(object : Observer<Translation> {
//                override fun onSubscribe(d: Disposable) {
//                    NLog.d(TAG, "开始采用subscribe连接")
//                }
//
//                override fun onNext(result: Translation) {
//                    // 步骤8：对返回的数据进行处理
//                    NLog.d(TAG, result.translateResult[0][0].tgt)
//                }
//
//                override fun onError(e: Throwable) {
//                    NLog.d(TAG, "请求失败")
//                }
//
//                override fun onComplete() {
//                    NLog.d(TAG, "请求成功")
//                }
//            })
    }
}
