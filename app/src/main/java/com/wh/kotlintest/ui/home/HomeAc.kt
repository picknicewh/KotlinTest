package com.wh.kotlintest.ui.home

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.wh.kotlintest.R
import com.wh.kotlintest.bean.*
import com.wh.kotlintest.ui.story.StoryDetailActivity
import com.wh.kotlintest.util.DateUtil
import com.wh.kotlintest.util.G
import com.wh.kotlintest.util.OnItemClickListener
import com.wh.kotlintest.widget.BannerView
import com.wh.kotlintest.widget.LoadingDialog
import java.util.*

/**
 * 作者： wh
 * 时间：  2017/8/15
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class HomeAc : AppCompatActivity(), HomeContract.View, OnItemClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, HomeMenuAdapter.onSubscribedListener, HomeContentAdapter.OnItemClickListener {


    /**
     * 抽屉
     */
    private var drawerLayout: DrawerLayout? = null
    /**
     * 侧滑菜单开关
     */
    private var toggle: ActionBarDrawerToggle? = null
    /**
     * 数据处理
     */
    private var homePresenter: HomePresenter? = null
    private var adapter: HomeMenuAdapter? = null
    private var rv_menu: RecyclerView? = null
    private var subscribedVoList: MutableList<ThemeVo.SubscribedVo>? = null
    /**
     * 轮播图
     */
    private var bannerView: BannerView? = null
    /**
     * 对话框
     */
    private var dialog: LoadingDialog? = null
    /**
     * 导航栏
     */
    private var mToolbar: Toolbar? = null
    /**
     * 日期
     */
    private var tvDate: TextView? = null
    /**
     * 主要内容
     */
    private var rv_story: RecyclerView? = null
    /**
     * 主内容适配器
     */
    private var contentAdapter: HomeContentAdapter? = null
    private var storiesVoList: MutableList<StoriesVo>? = null
    /**
     * 刷新
     */
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var page = 0
    /**
     * 主编列表
     */
    private var rv_editor: RecyclerView? = null
    /**
     * 主编适配器
     */
    private var editAdapter: HomeEditAdapter? = null
    /**
     * 主编数据列表
     */
    private var editorsVoList: MutableList<EditorsVo>? = null
    /**
     * 主题id
     */
    private var themeId: Int = 0
    /**
     * 菜单项--》登录
     */
    private var menuBell: MenuItem? = null
    /**
     * 菜单项--》切换模式
     */
    private var menuShare: MenuItem? = null
    /**
     * 菜单项--》关注主题
     */
    private var menuAdd: MenuItem? = null
    /**
     * 菜单项--》取消关注主题
     */
    private var menuMinus: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        bannerView = findViewById(R.id.bannerView) as BannerView
        tvDate = findViewById(R.id.tv_date) as TextView
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        setToolBar()
        setLeftMenu()
        setContent()
        setEditor()
        homePresenter = HomePresenter(this, this)
        homePresenter!!.getThemeVo()
        homePresenter!!.getLatestStrory()
        swipeRefreshLayout!!.setOnRefreshListener(this)
    }

    private fun setEditor() {
        editorsVoList = ArrayList<EditorsVo>()
        rv_editor = findViewById(R.id.rv_editor) as RecyclerView
        rv_editor!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        editAdapter = HomeEditAdapter(this, editorsVoList as ArrayList<EditorsVo>)
        rv_editor!!.adapter = editAdapter
    }

    private fun setLeftMenu() {
        rv_menu = findViewById(R.id.rv_menu) as RecyclerView
        subscribedVoList = ArrayList<ThemeVo.SubscribedVo>()
        adapter = HomeMenuAdapter(this, subscribedVoList)
        rv_menu!!.isNestedScrollingEnabled = false
        rv_menu!!.isFocusable = false
        rv_menu!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_menu!!.adapter = adapter
        adapter!!.setOnItemClickListener(this)
        adapter!!.setOnSubscribedListener(this)
    }

    private fun setContent() {
        rv_story = findViewById(R.id.rv_story) as RecyclerView
        storiesVoList = ArrayList<StoriesVo>()
        contentAdapter = HomeContentAdapter(this, storiesVoList!!)
        rv_story!!.isNestedScrollingEnabled = false
        rv_story!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_story!!.adapter = contentAdapter
        contentAdapter!!.setOnItemClickListener(this)
    }
    override fun onItemClick(position: Int) {
        val intent = android.content.Intent(this, StoryDetailActivity::class.java)
        intent.putExtra("storyId", storiesVoList!![position].id)
        startActivity(intent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bell -> {
            }
            R.id.share -> {
            }
            R.id.add -> {
                homePresenter!!.unAttentionByThemeId()
                setMenuStatus(false, true)
            }
            R.id.minus -> {
                homePresenter!!.attentionByThemeId()
                setMenuStatus(false, false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.action_home_menu, menu)
        menuBell = menu.findItem(R.id.bell)
        menuShare = menu.findItem(R.id.share)
        menuAdd = menu.findItem(R.id.add)
        menuMinus = menu.findItem(R.id.minus)
        setMenuStatus(true, false)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * 设置导航栏菜单的显示与隐藏
     * @param  isCollect 改主题是否关注
     * @param isHome 是否首页
     */
    private fun setMenuStatus(isHome: Boolean, isCollect: Boolean) {
        menuBell!!.isVisible = isHome
        menuShare!!.isVisible = isHome
        menuAdd!!.isVisible = !isCollect && !isHome
        menuMinus!!.isVisible = isCollect && !isHome
    }

    fun setToolBar() {
        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        mToolbar = findViewById(R.id.toolbar) as Toolbar
        mToolbar!!.setTitle(R.string.home)
        mToolbar!!.setTitleTextColor(Color.WHITE)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setHomeButtonEnabled(true)//设置返回键可用
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mToolbar!!.setNavigationIcon(R.mipmap.ic_more)
        mToolbar!!.setNavigationOnClickListener(this)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_slide_menu, R.string.close_slide_menu)
        drawerLayout!!.setDrawerListener(toggle)
        toggle!!.syncState()
    }

    override fun setLatestStoryVo(latestStoryList: LatestStoryVo) {
        swipeRefreshLayout!!.isRefreshing = false
        if (latestStoryList != null) {
            bannerView!!.setBannerVoList(latestStoryList.top_stories, this)
            tvDate!!.text = "今日热闻"
            rv_editor!!.visibility = View.GONE
            this.storiesVoList!!.clear()
            this.storiesVoList!!.addAll(latestStoryList.stories!!)
            if (contentAdapter != null) {
                contentAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun setThemeVo(themeVo: ThemeVo) {
        subscribedVoList!!.clear()
        val subscribedVo = ThemeVo.SubscribedVo()
        subscribedVo.name = "首页"
        subscribedVoList!!.add(subscribedVo)
        for (i in 0..themeVo.subscribed!!.size - 1) {
            val othersVo = themeVo.subscribed!![i]
            othersVo.isSubscribed = false
            subscribedVoList!!.add(othersVo)
        }
        for (i in 0..themeVo.others!!.size - 1) {
            val subscribed = themeVo.others!![i]
            subscribed.isSubscribed = true
            subscribedVoList!!.add(subscribed)
        }
        if (adapter != null) {
            adapter!!.initData(0)
            adapter!!.notifyDataSetChanged()
        }
    }

    override fun setThemeDetailVo(detailVo: ThemeDetailVo) {
        swipeRefreshLayout!!.isRefreshing = false
        if (detailVo != null) {
            //轮播图
            bannerView!!.setSingleBanner(detailVo, this)
            //主编
            tvDate!!.text = "主编"
            this.editorsVoList!!.clear()
            this.editorsVoList!!.addAll(detailVo.editors!!)
            if (editAdapter != null) {
                editAdapter!!.notifyDataSetChanged()
            }
            //内容
            this.storiesVoList!!.clear()
            this.storiesVoList!!.addAll(detailVo.stories!!)
            if (contentAdapter != null) {
                contentAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun getDate(): String {
        page++
        return DateUtil.getFormatDateBetweenDays(page)
    }

    override fun getThemeId(): Int {
        return themeId
    }

    override fun attentionSuccess(isAttention: Boolean) {
        if (isAttention) {
            showMessage("已关注")
        } else {
            showMessage("取消关注")
        }
        homePresenter!!.getThemeVo()
    }

    override fun onSubscribed(view: View, position: Int) {
        if (subscribedVoList!![position].isSubscribed)
            homePresenter!!.unAttentionByThemeId()
        else
            homePresenter!!.attentionByThemeId()
    }

    override fun onClick(view: View, position: Int) {
        drawerLayout!!.closeDrawer(GravityCompat.START)
        if (position == 0) {
            themeId = 0
            homePresenter!!.getLatestStrory()
            mToolbar!!.title = "首页"
            setMenuStatus(true, false)
        } else {
            themeId = subscribedVoList!![position].id
            homePresenter!!.getStroyByThemeId()
            mToolbar!!.title = subscribedVoList!![position].name
            setMenuStatus(false, false)
        }
    }

    override fun showProgress() {
        if (dialog == null) {
            dialog = LoadingDialog(this)
        }
        dialog!!.show()
    }

    override fun hideProgress() {
        dialog!!.dismiss()
    }

    override fun showMessage(message: String) {
        G.showToast(this, message)
    }

    override fun onClick(v: View) {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout!!.openDrawer(GravityCompat.START)
        }
    }

    override fun onRefresh() {
        if (themeId != 0) {
            homePresenter!!.getStroyByThemeId()
        } else {
            homePresenter!!.getLatestStrory()
        }
    }
}

