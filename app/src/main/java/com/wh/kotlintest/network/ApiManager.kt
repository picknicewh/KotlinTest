package com.wh.kotlintest.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 作者： wh
 * 时间：  2017/8/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
object ApiManager {

    private var okHttpClient: OkHttpClient? = null
    private var apiManagerService: ApiHttpService? = null
    fun  getApiManagerService(): ApiHttpService {
        if (apiManagerService == null) {
            val retrofit = Retrofit.Builder()
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ServiceConfigManager.SERVER_IP)
                    .build()
            apiManagerService = retrofit.create(ApiHttpService::class.java)
        }
        return apiManagerService!!
    }
    private fun getOkHttpClient(): OkHttpClient {
        val level = HttpLoggingInterceptor.Level.BODY
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("OkHttp", "Message:" + message) })
        loggingInterceptor.level = level
        if (okHttpClient == null) {
            okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build()
        }
        return okHttpClient!!
    }

}

