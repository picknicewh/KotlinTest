package com.wh.kotlintest.base

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.wh.kotlintest.R
import com.wh.kotlintest.widget.LoadingDialog


/**
 * 作者： wh
 * 时间：  2017/8/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
abstract class BaseActivity : AppCompatActivity(),BaseView{
    private var loadDialog: LoadingDialog?=null
    private var  mToolbar: Toolbar?=null
    private var content:FrameLayout?=null
    private var navigationTitle:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolBar()
        setContentView(R.layout.layout_common_title)
        //内容
        content = findViewById(R.id.content) as FrameLayout
        LayoutInflater.from(this).inflate(getLayoutId(),content,true)
        initView()
        mToolbar = findViewById(R.id.toolbar) as Toolbar
        mToolbar!!.setTitle(navigationTitle)
        mToolbar!!.setTitleTextColor(Color.WHITE)
        setSupportActionBar(mToolbar)
        mToolbar!!.setNavigationIcon(R.mipmap.ic_back)
        mToolbar!!.setNavigationOnClickListener { v ->  setOnNavigationOnClickListener() }
    }
    //获取内容的布局
    abstract fun getLayoutId():Int
    abstract fun initView()
    abstract fun setToolBar()
    override  fun showMessage(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }
     fun setOnNavigationOnClickListener(){
         finish()
     }
    override fun showProgress() {
        if (loadDialog==null){
            loadDialog = LoadingDialog(this)
        }
       loadDialog!!.show()
    }
    override fun hideProgress() {
     loadDialog!!.dismiss()
    }
    fun setNavigationTitle(navigationTitle:String){
        this.navigationTitle = navigationTitle
    }
}


