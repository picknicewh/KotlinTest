package com.wh.kotlintest.ui

import android.view.Menu
import android.view.MenuInflater
import com.wh.kotlintest.R
import com.wh.kotlintest.base.BaseActivity

/**
 * 作者： wh
 * 时间：  2017/8/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class HomeActivity : BaseActivity() {


    override fun setToolBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
    override fun initView() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.action_detail_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}
