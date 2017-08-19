package com.wh.kotlintest.ui.story

import android.content.Context

import com.wh.kotlintest.bean.StoryDetail
import com.wh.kotlintest.network.ApiManager

import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * 作者： wh
 * 时间：  2017/8/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class StoryDetailPresenter(private val view: StoryDetailContract.View, private val context: Context) : StoryDetailContract.Presenter {
    private var mSubscription: Subscription? = null
    override fun subscribe() {
        getStoryDetail()
    }

    override fun unSubscribe() {
        if (mSubscription != null && mSubscription!!.isUnsubscribed) {
            mSubscription!!.unsubscribe()
        }
    }

    override fun getStoryDetail() {
        mSubscription = ApiManager.getApiManagerService().getStoryDetail(view.getStoryId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<StoryDetail>() {
                    override fun onStart() {
                        view.showProgress()
                    }

                    override fun onCompleted() {
                        view.hideProgress()
                    }

                    override fun onError(e: Throwable) {
                        view.hideProgress()
                        view.showMessage(e.message!!)
                    }

                    override fun onNext(storyDetail: StoryDetail?) {
                        if (storyDetail != null) {
                            view.setStoryDetailVo(storyDetail)
                        }
                    }
                })
    }
}
