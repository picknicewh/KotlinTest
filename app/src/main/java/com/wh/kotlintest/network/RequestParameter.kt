package com.wh.kotlintest.network

import android.text.TextUtils
import com.google.gson.Gson
import java.util.*

/**
 * 封装用于网络请求的网络地址和网络地址, 并按照格式输出 注意自己保证数据的完整性
 */
class RequestParameter {

    /** 保存网络请求的参数及值  */
    private var mParams: MutableMap<String, Any>? = null

    /**
     * 网络请求的参数中默认自带某些接口参数
     * boolean isSetName 是否设置用户名
     */
    init {
        this.mParams = HashMap<String, Any>()
    }

    /**
     * 设置用于网络请求的参数及参数值
     * @param name 参数名称
     * *
     * @param value 参数值
     */
    fun setParam(name: String, value: Any) {
        if (value is String) {
            if (!TextUtils.isEmpty(value)) {
                mParams!!.put(name, value)
            }
        } else {
            mParams!!.put(name, value)
        }
    }


    /**
     * post参数
     * @return
     */
    fun postParams(): String {
        val postInfoStr = Gson().toJson(mParams)
        return postInfoStr
    }

    /**
     * get参数
     * @return
     */
    val mapParams: Map<*, *>
        get() = mParams!!
}
