package com.wh.kotlintest.network

import com.wh.kotlintest.bean.LatestStoryVo
import com.wh.kotlintest.bean.StoryDetail
import com.wh.kotlintest.bean.ThemeDetailVo
import com.wh.kotlintest.bean.ThemeVo
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * 作者： wh
 * 时间：  2017/8/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
interface  ApiHttpService{
    //@QueryMap param: Map<String,Object>
    /**获取今天（最新的）的故事列表*/
    @GET("api/4/stories/latest")
    fun getSubject():Observable<LatestStoryVo>

    /**获取今天之前的故事列表*/
    @GET("api/4/stories/before/{date}")
    fun getSubjectByDate(@Path("themeId")  date :String):Observable<LatestStoryVo>

    /**获取主题信息详情*/
    @GET("/api/4/theme/{themeId}")
    fun getSubjectByThemeId(@Path("themeId")  themeId :Int):Observable<ThemeDetailVo>

    /**获取主题列表*/
    @GET("api/4/themes")
    fun getTheme():Observable<ThemeVo>

    /**获取故事详情*/
    @GET("api/4/story/{storyId}")
    fun getStoryDetail(@Path("storyId")  storyId :Int):Observable<StoryDetail>

   /**取消/关注*/
    @GET("api/4/theme/{themeId}/unsubscribe")
    fun unSubscribe(@Field("themeId")  themeId :Int):Observable<String>
    /**取消/关注*/

    @GET("api/4/theme/{themeId}/subscribe")
    fun subscribe(@Path("themeId")  themeId :Int):Observable<String>

    /**收藏*/
    @GET("/api/4/favorite/{storyId}")
    fun collection(@Path("storyId")  storyId :Int):Observable<String>

}
