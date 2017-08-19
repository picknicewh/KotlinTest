package com.wh.kotlintest.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.wh.kotlintest.R
import com.wh.kotlintest.bean.LatestStoryVo
import com.wh.kotlintest.network.GlideUtils

/**
 * 作者： wh
 * 时间：  2017/8/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class IndicatorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    /**
     * 图片
     */
    private var iv_image: ImageView? = null
    /**
     * 标题
     */
    private var tv_title: TextView? = null

    init {
        initView(context)
    }

    private fun initView(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.item_indicator_content, this)
        iv_image = findViewById(R.id.iv_image) as ImageView
        tv_title = findViewById(R.id.tv_title) as TextView
    }


    fun setBannerVo(bannerVo: LatestStoryVo.TopStoriesVo) {
        GlideUtils.loadImageView(context!!, bannerVo.image!!, iv_image!!)
        tv_title!!.text = bannerVo.title
    }
}
