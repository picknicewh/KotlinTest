package com.wh.kotlintest.bean

/**
 * 作者： wh
 * 时间：  2017/8/17
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class LatestStoryVo {

    /**
     * date : 20170817
     * stories : [{"images":["https://pic1.zhimg.com/v2-dd83365f619da4213dca1109cd441104.jpg"],"type":0,"id":9573761,"ga_prefix":"081710","title":"《火影忍者》中角色的名字，原来是有特殊含义的"},{"images":["https://pic4.zhimg.com/v2-9ca9cd88a20e2ec2d03017182413f06f.jpg"],"type":0,"id":9574487,"ga_prefix":"081709","title":"假设《泰坦尼克号》事件现在发生，我们能把人救出来吗？"},{"images":["https://pic2.zhimg.com/v2-6b8ca78590b5834d03e1507c24217ced.jpg"],"type":0,"id":9570680,"ga_prefix":"081708","title":"别觉得浮潜很简单，不做好功课都是生命的代价"},{"images":["https://pic3.zhimg.com/v2-bff4850981cdda2314e4fc2e70c0700e.jpg"],"type":0,"id":9574572,"ga_prefix":"081707","title":"越野跑大概是跑步的终极版本，最终回归原始"},{"images":["https://pic2.zhimg.com/v2-7449d5a93f655f5ce5ccbdd95d9cb855.jpg"],"type":0,"id":9573414,"ga_prefix":"081707","title":"如果巴赫、贝多芬（没耳聋）、莫扎特还活着，他们会喜欢摇滚乐吗？"},{"images":["https://pic4.zhimg.com/v2-2e73803ab8ac01e09d162c8b261a214f.jpg"],"type":0,"id":9574031,"ga_prefix":"081707","title":"未来几年，这块市场会越来越大，就像当年的美国一样"},{"images":["https://pic3.zhimg.com/v2-c59430a476825c09fa9e9f42e7270082.jpg"],"type":0,"id":9572913,"ga_prefix":"081706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-71ef663585467c1be2366de56b38350d.jpg","type":0,"id":9574031,"ga_prefix":"081707","title":"未来几年，这块市场会越来越大，就像当年的美国一样"},{"image":"https://pic1.zhimg.com/v2-c359b0616096ccb1810a8afaaf8810b0.jpg","type":0,"id":9573414,"ga_prefix":"081707","title":"如果巴赫、贝多芬（没耳聋）、莫扎特还活着，他们会喜欢摇滚乐吗？"},{"image":"https://pic4.zhimg.com/v2-210fc543b00744bc05f1c1af8db1a32b.jpg","type":0,"id":9573831,"ga_prefix":"081617","title":"台湾突发史上最大规模停电，这口「锅」该谁背？"},{"image":"https://pic3.zhimg.com/v2-b41ee58e060428cdc169f4297632cdde.jpg","type":0,"id":9573667,"ga_prefix":"081615","title":"你相信有上帝 / 神灵 / 鬼魂 / 佛的存在吗？"},{"image":"https://pic1.zhimg.com/v2-6a189ac9a6a7187754cf9a5f88458448.jpg","type":0,"id":9572271,"ga_prefix":"081613","title":"有了高铁和互联网，你会从一线城市回到三四线城市吗？"}]
     */

    var date: String? = null
    var stories: List<StoriesVo>? = null
    var top_stories: List<TopStoriesVo>? = null

    class TopStoriesVo {
        /**
         * image : https://pic2.zhimg.com/v2-71ef663585467c1be2366de56b38350d.jpg
         * type : 0
         * id : 9574031
         * ga_prefix : 081707
         * title : 未来几年，这块市场会越来越大，就像当年的美国一样
         */

        var image: String? = null
        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
    }
}
