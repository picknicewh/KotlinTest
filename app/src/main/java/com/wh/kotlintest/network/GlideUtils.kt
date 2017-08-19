package com.wh.kotlintest.network

import android.content.Context
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget

/**
 * 作者： wanghua
 * 时间： 2017/3/7
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
object GlideUtils {
    /**
     * 默认加载
     * @param  mContext 上下文本
     * *
     * @param  path 图片路径
     * @param  mImageView 图片控件
     */
    fun loadImageView(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path)
                //   .placeholder(R.mipmap.ic_error_image)
                //  .error(R.mipmap.ic_error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                //                .centerCrop()
                //   .thumbnail(0.1f)//支持加载1/10的缩略图，再加载全图
                .into(mImageView)

    }

    /**
     * 加载指定大小
     * @param  mContext 上下文本
     * *
     * @param  path 图片路径
     * *
     * @param  width 指定宽度
     * *
     * @param height 指定高度
     * @param  mImageView 图片控件
     */
    fun loadImageViewSize(mContext: Context, path: String, width: Int, height: Int, mImageView: ImageView) {
        Glide.with(mContext).load(path).override(width, height).into(mImageView)
    }

    /**
     * 设置跳过内存缓存
     * @param  mContext 上下文本
     * *
     * @param  path 图片路径
     * @param  mImageView 图片控件
     */
    fun loadImageViewCache(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).skipMemoryCache(true).into(mImageView)
    }

    /**
     * 设置下载优先级
     * @param  mContext 上下文本
     * *
     * @param  path 图片路径
     * @param  mImageView 图片控件
     */
    fun loadImageViewPriority(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).priority(Priority.NORMAL).into(mImageView)
    }

    /**
     * 策略解说：
     *
     *
     * all:缓存源资源和转换后的资源
     *
     *
     * none:不作任何磁盘缓存
     *
     *
     * source:缓存源资源
     *
     *
     * result：缓存转换后的资源
     */
    //设置缓存策略
    fun loadImageViewDiskCache(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView)
    }

    /**
     * api也提供了几个常用的动画：比如crossFade()
     */

    //设置加载动画
    fun loadImageViewAnim(mContext: Context, path: String, anim: Int, mImageView: ImageView) {
        Glide.with(mContext).load(path).animate(anim).into(mImageView)
    }

    /**
     * 设置缩略图支持
     */

    fun loadImageViewThumbnail(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView)
    }

    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //设置动态转换
    fun loadImageViewCrop(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).centerCrop().into(mImageView)
    }

    //设置动态GIF加载方式
    fun loadImageViewDynamicGif(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).asGif().into(mImageView)
    }

    //设置静态GIF加载方式
    fun loadImageViewStaticGif(mContext: Context, path: String, mImageView: ImageView) {
        Glide.with(mContext).load(path).asBitmap().into(mImageView)
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

    //设置监听请求接口
    fun loadImageViewListener(mContext: Context, path: String, mImageView: ImageView, requstlistener: RequestListener<String, GlideDrawable>) {
        Glide.with(mContext).load(path).listener(requstlistener).into(mImageView)
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
    fun loadImageViewContent(mContext: Context, path: String, simpleTarget: SimpleTarget<GlideDrawable>) {
        Glide.with(mContext).load(path).centerCrop().into(simpleTarget)
    }

    //清理磁盘缓存
    fun GuideClearDiskCache(mContext: Context) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache()
    }

    //清理内存缓存
    fun GuideClearMemory(mContext: Context) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory()
    }

}
