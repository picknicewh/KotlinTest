package com.wh.kotlintest.ui.home;

import android.support.v7.util.DiffUtil;

import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/8/19
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class HomeMenuDiffCallBack extends DiffUtil.Callback{
    private Map<Integer,Boolean> oldList;
    private Map<Integer,Boolean> newList;
    public HomeMenuDiffCallBack( Map<Integer,Boolean> oldList,  Map<Integer,Boolean> newList){
        this.oldList = oldList;
        this.newList =newList;
    }
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getClass().equals(newList.get(newItemPosition).getClass());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        boolean oldData = oldList.get(oldItemPosition);
        boolean newData = newList.get(newItemPosition);
        return oldData==newData;
    }
}
