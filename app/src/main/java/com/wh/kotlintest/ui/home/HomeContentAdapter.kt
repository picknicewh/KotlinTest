package com.wh.kotlintest.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.wh.kotlintest.R
import com.wh.kotlintest.bean.StoriesVo
import com.wh.kotlintest.network.GlideUtils
import com.wh.kotlintest.util.G

/**
 * 作者： wh
 * 时间：  2017/8/15
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class HomeContentAdapter(private val context: Context, private val storiesVoList: List<StoriesVo>) : RecyclerView.Adapter<HomeContentAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_content, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val storiesVo = storiesVoList[position]
        val lp: RelativeLayout.LayoutParams
        if (storiesVo.images != null && storiesVo.images!!.size > 0) {
            GlideUtils.loadImageView(context, storiesVo.images!![0], holder.iv_image)
            holder.iv_image.visibility = View.VISIBLE
            lp = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0, 0, G.dp2px(context, 80.0), 0)
            holder.tv_title.layoutParams = lp
        } else {
            lp = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            holder.tv_title.layoutParams = lp
            holder.iv_image.visibility = View.GONE
        }
        holder.tv_title.text = storiesVo.title
        holder.itemView.setOnClickListener { v ->
            if (onItemClickListener != null) {
                onItemClickListener!!.onItemClick( position)
            }
        }
    }

    override fun getItemCount(): Int {
        return storiesVoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_title: TextView
        val iv_image: ImageView

        init {
            tv_title = itemView.findViewById(R.id.tv_title) as TextView
            iv_image = itemView.findViewById(R.id.iv_image) as ImageView
            itemView.tag = this
        }
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    interface OnItemClickListener {
        fun onItemClick( position: Int)
    }
}
