package com.wh.kotlintest.widget

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.wh.kotlintest.R

/**
 * 作者： wh
 * 时间：  2017/8/15
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
class BannerDosAdapter(private var mPosition: Int, private var size: Int) : RecyclerView.Adapter<BannerDosAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_indicator_dos, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.iv_dos.visibility = if (size > 1) View.VISIBLE else View.GONE
        if (position == mPosition) {
            holder.iv_dos.setImageResource(R.drawable.circle_dos_trans)
        } else {
            holder.iv_dos.setImageResource(R.drawable.circle_dos)
        }
    }

    override fun getItemCount(): Int {
        return size
    }

    fun iniData(position: Int, size: Int) {
        mPosition = position
        this.size = size

        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_dos: ImageView

        init {
            iv_dos = itemView.findViewById(R.id.iv_dos) as ImageView
            itemView.tag = this
        }
    }
}
