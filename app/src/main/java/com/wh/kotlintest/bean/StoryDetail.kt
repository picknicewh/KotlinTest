package com.wh.kotlintest.bean

/**
 * 作者： wh
 * 时间：  2017/8/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class StoryDetail {

    /**
     * body
     * image_source : meg.dai / CC BY-ND
     * title : 小时候只觉得停电很好玩，却没想过为什么大家会一起停电
     * image : https://pic3.zhimg.com/v2-59b4550c7b511c8f043e4212b2712482.jpg
     * share_url : http://daily.zhihu.com/story/9575063
     * js : []
     * ga_prefix : 081716
     * images : ["https://pic3.zhimg.com/v2-829c441e8d43618d186a4a3ea2aad2f2.jpg"]
     * type : 0
     * id : 9575063
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */
    var body: String? = null
    var image_source: String? = null
    var title: String? = null
    var image: String? = null
    var share_url: String? = null
    var ga_prefix: String? = null
    var type: Int = 0
    var id: Int = 0
    var js: List<*>? = null
    var images: List<String>? = null
    var css: List<String>? = null
}
