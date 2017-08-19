package com.wh.kotlintest.ui.story

import com.wh.kotlintest.base.BasePresenter
import com.wh.kotlintest.base.BaseView
import com.wh.kotlintest.bean.StoryDetail

/**
 * 作者： wh
 * 时间：  2017/8/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class StoryDetailContract {
    interface View : BaseView {
        fun setStoryDetailVo(storyDetail: StoryDetail)
        fun getStoryId():Int

    }

    internal interface Presenter : BasePresenter {
        fun getStoryDetail()

    }
}
