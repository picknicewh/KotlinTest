package com.wh.kotlintest.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue.*
import android.view.Gravity
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

/**
 * ================================================
 * 作    者： wh
 * 时    间： 2016/7/8
 * 描    述：工具类汇总
 * 版    本：
 * 修订历史：
 * 主要接口：
 * ================================================
 */
object G {

    /**
     * 调试信息
     */
    var DEBUG = true
    /**
     * toast提示
     */
    private var toast: Toast? = null

    /**
     * 尺寸
     */
    object size {
        /**
         * 屏幕宽
         */
        var W = 480
        /**
         * 屏幕高
         */
        var H = 800
    }

    /**
     * 截屏
     */
    fun screenshots(activity: Activity, isFullScreen: Boolean) {
        val mFileTemp = File(activity.cacheDir, "screenshots.jpg")
        try {
            //View是你需要截图的View
            val decorView = activity.window.decorView
            decorView.isDrawingCacheEnabled = true
            decorView.buildDrawingCache()
            val b1 = decorView.drawingCache
            // 获取状态栏高度 /
            val frame = Rect()
            activity.window.decorView.getWindowVisibleDisplayFrame(frame)
            val statusBarHeight = frame.top
            // 获取屏幕长和高 Get screen width and height
            val width = activity.windowManager.defaultDisplay.width
            val height = activity.windowManager.defaultDisplay.height
            // 去掉标题栏 Remove the statusBar Height
            val bitmap: Bitmap
            if (isFullScreen) {
                bitmap = Bitmap.createBitmap(b1, 0, 0, width, height)
            } else {
                bitmap = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight)
            }
            decorView.destroyDrawingCache()
            val out = FileOutputStream(mFileTemp)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 初始化屏幕尺寸
     */
    fun initDisplaySize(activity: Activity) {
        val mDisplayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(mDisplayMetrics)
        size.W = mDisplayMetrics.widthPixels
        size.H = mDisplayMetrics.heightPixels
    }

    /**
     * 提示

     * @param msg 提示信息
     */
    fun showToast(context: Context, msg: String) {
        if (toast != null) {
            toast!!.cancel()
        }
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast!!.setGravity(Gravity.TOP, 0, size.H / 4)
        toast!!.show()
    }

    /**
     * 记录调试信息

     * @param msg 调试信息
     */
    fun log(msg: Any) {
        if (DEBUG) {
            Log.i("TAG", msg.toString())
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)

     * @param context
     * *
     * @param dpValue
     * *
     * @return
     */
    fun dp2px(context: Context, dpValue: Double): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp

     * @param context
     * *
     * @param pxValue
     * *
     * @return
     */
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 判断字符串是否为空
     */
    fun isEmteny(values: String?): Boolean {
        if (null == values || "" == values || "null" == values) {
            return true
        }
        return false
    }

    /**
     * 删除缓存
     */
    fun clearCacheFolder(dir: File?, numDays: Long): Int {
        var deletedFiles = 0
        if (dir != null && dir.isDirectory) {
            try {
                for (child in dir.listFiles()) {
                    if (child.isDirectory) {
                        deletedFiles += clearCacheFolder(child, numDays)
                    }
                    if (child.lastModified() < numDays) {
                        if (child.delete()) {
                            deletedFiles++
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return deletedFiles
    }

    /**
     * 清除WebView缓存
     */
    fun clearWebViewCache(context: Context) {
        //清理Webview缓存数据库
        try {
            context.deleteDatabase("webview.db")
            context.deleteDatabase("webviewCache.db")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //WebView 缓存文件
        val appCacheDir = context.getDir("cache", Context.MODE_PRIVATE)
        //删除webview 缓存 缓存目录
        if (appCacheDir.exists()) {
            context.deleteFile(appCacheDir.absolutePath)
        }
    }

    /**
     * 进入权限管理页面

     * @param context
     */
    fun getAppDetailSettingIntent(context: Context) {
        val localIntent = Intent()
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
            localIntent.data = Uri.fromParts("package", context.packageName, null)
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.action = Intent.ACTION_VIEW
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
        }
        context.startActivity(localIntent)
    }

    /**
     * 判断是否全为空格
     */
    fun isAllSpace(tag: String): Boolean {
        val chars = tag.toCharArray()
        var count = 0
        for (i in chars.indices) {
            if (chars[i] == ' ') {
                count++
            }
        }
        if (count == chars.size) {
            return true
        } else {
            return false
        }
    }

    fun applyDimension(unit: Int, value: Float, metrics: DisplayMetrics): Float {
        when (unit) {
            COMPLEX_UNIT_PX -> return value
            COMPLEX_UNIT_DIP -> return value * metrics.density
            COMPLEX_UNIT_SP -> return value * metrics.scaledDensity
            COMPLEX_UNIT_PT -> return value * metrics.xdpi * (1.0f / 72)
            COMPLEX_UNIT_IN -> return value * metrics.xdpi
            COMPLEX_UNIT_MM -> return value * metrics.xdpi * (1.0f / 25.4f)
        }
        return 0f
    }
}


