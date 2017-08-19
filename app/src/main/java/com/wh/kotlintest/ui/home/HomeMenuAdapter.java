package com.wh.kotlintest.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wh.kotlintest.R;
import com.wh.kotlintest.bean.ThemeVo;
import com.wh.kotlintest.util.G;
import com.wh.kotlintest.util.OnItemClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/8/15
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class HomeMenuAdapter extends RecyclerView.Adapter<HomeMenuAdapter.ViewHolder> {
    private Context context;
    private List<ThemeVo.SubscribedVo> subscribedVoList;
    private Map<Integer,Boolean> checkList;
    private Map<Integer,Boolean> newCheckList;
    private OnItemClickListener onItemClickListener;
    private onSubscribedListener onSubscribedListener;
    public HomeMenuAdapter(Context context,List<ThemeVo.SubscribedVo> subscribedVoList){
        this.context = context;
        this.subscribedVoList = subscribedVoList;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_menu_home,null));
    }
    public void initData(int position){
        checkList = new HashMap<>();
        newCheckList = new HashMap<>();
        for (int i = 0 ; i <subscribedVoList.size();i++){
            if (i==position){
                checkList.put(i,true);
            }else {
                checkList.put(i,false);
            }
        }
        newCheckList = checkList;
     //   notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ThemeVo.SubscribedVo subscribedVo = subscribedVoList.get(position);
      if (position==0){
          holder.iv_right.setVisibility(View.GONE);
          holder.tv_home.setTextColor(ContextCompat.getColor(context,R.color.main_theme_color));
          Drawable drawable =  ContextCompat.getDrawable(context,R.mipmap.ic_home);
          drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
          holder.tv_home.setCompoundDrawables(drawable,null,null,null);
          holder.tv_home.setCompoundDrawablePadding(G.INSTANCE.dp2px(context,10));
          if (subscribedVo.isSubscribed()) holder.iv_right.setImageResource(R.mipmap.ic_arrow_right);
          else holder.iv_right.setImageResource(R.mipmap.ic_add_grey);
      }else {
          holder.iv_right.setVisibility(View.VISIBLE);
          holder.tv_home.setTextColor(ContextCompat.getColor(context,R.color.main_text_color));
      }
       holder.tv_home.setText(subscribedVo.getName());
        if (checkList.get(position)){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.divine_line_color));
        }else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               initData(position);
                dataChange();
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(v,position);
                }
            }
        });
        holder.iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (onSubscribedListener!=null){
                  onSubscribedListener.onSubscribed(v,position);
              }
            }
        });
    }
    private  DiffUtil.DiffResult diffResult;
    private void dataChange(){
        diffResult = DiffUtil.calculateDiff(
                new HomeMenuDiffCallBack(checkList,newCheckList));
        diffResult.dispatchUpdatesTo(this);
    }
    @Override
    public int getItemCount() {
        return subscribedVoList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
       private TextView tv_home;
       private ImageView iv_right;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_home = (TextView) itemView.findViewById(R.id.tv_home);
            iv_right = (ImageView)itemView.findViewById(R.id.iv_right);
            itemView.setTag(this);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnSubscribedListener(HomeMenuAdapter.onSubscribedListener onSubscribedListener) {
        this.onSubscribedListener = onSubscribedListener;
    }

    public  interface  onSubscribedListener{
      void  onSubscribed(View view,int position);
  }
}
