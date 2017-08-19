package com.wh.kotlintest.ui.home

import com.wh.kotlintest.base.BasePresenter
import com.wh.kotlintest.base.BaseView
import com.wh.kotlintest.bean.LatestStoryVo
import com.wh.kotlintest.bean.ThemeDetailVo
import com.wh.kotlintest.bean.ThemeVo

/**
 * 作者： wh
 * 时间：  2017/8/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class HomeContract {
    interface View : BaseView {
        fun setLatestStoryVo(latestStoryList: LatestStoryVo)
        fun setThemeVo(themeVo: ThemeVo)
        fun setThemeDetailVo(detailVo: ThemeDetailVo)
        fun getDate():String
        fun getThemeId():Int
        fun attentionSuccess(isAttention: Boolean)
    }

    internal interface Presenter : BasePresenter {
        fun getLatestStrory()
        fun getSubjectByDate()
        fun getThemeVo()
        fun getStroyByThemeId()
        fun attentionByThemeId()
        fun unAttentionByThemeId()
    }
}
