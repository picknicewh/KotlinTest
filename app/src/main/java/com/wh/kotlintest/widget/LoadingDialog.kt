package com.wh.kotlintest.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.wh.kotlintest.R

/**
 * 作者： wh
 * 时间：  2017/8/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class LoadingDialog(context: Context?) : Dialog(context,R.style.dialog) {
    private var iv_loading:ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        iv_loading = findViewById(R.id.iv_loading) as ImageView
        startLoading()
    }
    private fun startLoading(){
       var animation: Animation = AnimationUtils.loadAnimation(context,R.anim.loading)
        iv_loading!!.startAnimation(animation)
    }
}
