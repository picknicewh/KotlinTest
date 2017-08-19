package com.wh.kotlintest.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * 作者： wh
 * 时间：  2017/8/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
object DateUtil {

    /**
     * 以yyyy-MM-dd格式获取日期
     * @param date 日期
     */
    fun getFormatDate(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd").format(date)
    }

    /**
     * 以yyyyMM-d格式获取与今天相差的days天的字符串数据
     * @param days 相隔天数
     */
    fun getFormatDateBetweenDays(days: Int): String {
        val date = Date(System.currentTimeMillis())
        val between = (days * 24 * 60 * 60 * 1000).toLong()
        val betweenTime = date.time - between
        val mDate = Date(betweenTime)
        return SimpleDateFormat("yyyyMMdd").format(mDate)
    }
}
