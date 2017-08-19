package com.wh.kotlintest.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wh.kotlintest.R
import com.wh.kotlintest.bean.EditorsVo
import com.wh.kotlintest.network.GlideUtils
import com.wh.kotlintest.util.OnItemClickListener
import com.wh.kotlintest.widget.CircleImageView

/**
 * 作者： wh
 * 时间：  2017/8/15
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class HomeEditAdapter(private val context: Context, private val editorsVoList: List<EditorsVo>) : RecyclerView.Adapter<HomeEditAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_editor, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val editorsVo = editorsVoList[position]
        GlideUtils.loadImageView(context, editorsVo.avatar!!, holder.iv_image)
       /* holder.itemView.setOnClickListener {
            v ->var  diffResult:DiffUtil.DiffResult =DiffUtil.calculateDiff(AdapterDiffCallBack(null, null),false)
             diffResult.dispatchUpdatesTo(this)

        }*/

    }

    override fun getItemCount(): Int {
        return editorsVoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_image: CircleImageView

        init {
            iv_image = itemView.findViewById(R.id.civ_icon) as CircleImageView
            itemView.tag = this
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}
