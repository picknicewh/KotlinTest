package com.wh.kotlintest.ui.story

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.wh.kotlintest.R
import com.wh.kotlintest.base.BaseActivity
import com.wh.kotlintest.bean.StoryDetail
import com.wh.kotlintest.network.GlideUtils
import com.wh.kotlintest.ui.onekeyshare.OnekeyShare
import com.wh.kotlintest.util.G



class StoryDetailActivity : BaseActivity(), StoryDetailContract.View {

    /**
     * 加载网页
     */
    private var mWebView: WebView? = null
    /**
     * 数据处理类
     */
    private var presenter: StoryDetailPresenter? = null
    /**
     * 标题图片
     */
    private var iv_image: ImageView? = null
    /**
     * 标题
     */
    private var tv_title: TextView? = null
    /**
     * 资源详情
     */
    private var tv_image_source: TextView? = null
    /**
     * 标题
     */
    private var rl_top: RelativeLayout? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_stoty_detail
    }

    override fun initView() {
        mWebView = findViewById(R.id.mWebView) as WebView
        iv_image = findViewById(R.id.iv_image) as ImageView
        tv_title = findViewById(R.id.tv_title) as TextView
        tv_image_source = findViewById(R.id.tv_image_source) as TextView
        rl_top = findViewById(R.id.rl_top) as RelativeLayout
        val mSetting = mWebView!!.settings
        mSetting.javaScriptEnabled = true
        mSetting.defaultTextEncodingName = "utf-8"
        presenter = StoryDetailPresenter(this, this)
        presenter!!.subscribe()
    }

    override fun setToolBar() {
        title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.action_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                showShare()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    var storyImage:String?=null
    var storyTitle:String?=null
    var shareUrl :String?=null
    override fun setStoryDetailVo(storyDetail: StoryDetail) {
        storyImage = storyDetail.image
        storyTitle = storyDetail.title
        shareUrl  =  storyDetail.share_url
        GlideUtils.loadImageView(this, storyDetail.image!!, iv_image!!)
        rl_top!!.visibility = if (G.isEmteny(storyDetail.image)) View.GONE else View.VISIBLE
        tv_title!!.text = storyDetail.title
        tv_image_source!!.text = storyDetail.image_source
        val buffer = StringBuffer()
        val css = "<link  rel=\"stylesheet\"  type=\"text/css\"  href=\"" + storyDetail.css!![0] + "\"/>"
        //buffer.append(css).append("\n");
        buffer.append(storyDetail.body)
        G.log("xxxxxxxx" + buffer.toString())
        mWebView!!.loadDataWithBaseURL(null, buffer.toString(), "text/html", "utf-8", null)
    }

    private fun showShare() {
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share))
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(shareUrl)
        // text是分享文本，所有平台都需要这个字段
        oks.setText(storyTitle)
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(storyImage)//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shareUrl)
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本")
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name))
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareUrl)
        // 启动分享GUI
        oks.show(this)
    }
    override fun getStoryId(): Int {
        return intent.getIntExtra("storyId", -1)
    }
}
