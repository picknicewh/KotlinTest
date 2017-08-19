package com.wh.kotlintest

import android.app.Application

/**
 * 作者： wh
 * 时间：  2017/8/19
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
 class MyApplication: Application(){

     var myApplication:MyApplication?=null
     fun getInstance():Application{
        if (myApplication!=null){
            myApplication = MyApplication()
        }
         return  myApplication!!
    }
    override fun onCreate() {
        super.onCreate()
    //    ShareSDK.initSDK(this)

    }
}