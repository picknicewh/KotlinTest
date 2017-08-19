package com.wh.kotlintest.ui.home

import android.content.Context

import com.wh.kotlintest.bean.LatestStoryVo
import com.wh.kotlintest.bean.ThemeDetailVo
import com.wh.kotlintest.bean.ThemeVo
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
class HomePresenter(private val context: Context, private val view: HomeContract.View) : HomeContract.Presenter {
    private var mSubscription: Subscription? = null
    override fun getLatestStrory() {
        mSubscription = ApiManager.getApiManagerService().getSubject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<LatestStoryVo>() {
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

                    override fun onNext(latestStory: LatestStoryVo?) {
                        if (latestStory != null) {
                            view.setLatestStoryVo(latestStory)
                        }
                    }
                })
    }

    override fun getSubjectByDate() {
        mSubscription = ApiManager.getApiManagerService().getSubjectByDate(view.getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<LatestStoryVo>() {
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

                    override fun onNext(latestStory: LatestStoryVo?) {
                        if (latestStory != null) {
                            view.setLatestStoryVo(latestStory)
                        }
                    }
                })
    }


    override fun getThemeVo() {
        mSubscription = ApiManager.getApiManagerService().getTheme()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ThemeVo>() {
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

                    override fun onNext(themeVo: ThemeVo?) {
                        if (themeVo != null) {
                            view.setThemeVo(themeVo)
                        }
                    }
                })
    }

    override fun getStroyByThemeId() {
        mSubscription = ApiManager.getApiManagerService().getSubjectByThemeId(view.getThemeId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ThemeDetailVo>() {
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

                    override fun onNext(themeDetailVo: ThemeDetailVo?) {
                        if (themeDetailVo != null) {
                            view.setThemeDetailVo(themeDetailVo)
                        }
                    }
                })
    }

    override fun attentionByThemeId() {
        mSubscription = ApiManager.getApiManagerService().subscribe(view.getThemeId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<String>() {
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

                    override fun onNext(data: String) {
                        view.attentionSuccess(true)
                    }
                })
    }

    override fun unAttentionByThemeId() {
        mSubscription = ApiManager.getApiManagerService().unSubscribe(view.getThemeId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<String>() {
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

                    override fun onNext(data: String) {
                        view.attentionSuccess(false)
                    }
                })
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        if (mSubscription != null && mSubscription!!.isUnsubscribed) {
            mSubscription!!.unsubscribe()
        }
    }
}
