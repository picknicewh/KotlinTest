package com.wh.kotlintest.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wh.kotlintest.R;
import com.wh.kotlintest.bean.LatestStoryVo;
import com.wh.kotlintest.bean.ThemeDetailVo;
import com.wh.kotlintest.ui.story.StoryDetailActivity;
import com.wh.kotlintest.util.G;
import com.wh.kotlintest.util.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/16
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BannerView extends LinearLayout implements ViewPager.OnPageChangeListener, OnItemClickListener {
    /**
     * 滑动页面
     */
    private ViewPager viewPager;
    /**
     * 圆点
     */
    private RecyclerView rv_dos;
    /**
     * 圆点适配器
     */
    private BannerDosAdapter dosAdapter;
    /**
     * 轮播图适配器
     */
    private IndicatorContentAdapter contentAdapter;
    /**
     * 轮播图内容view
     */
    private List<IndicatorView> indicatorViews;
    /**
     * 轮播图列表数据
     */
    private List<LatestStoryVo.TopStoriesVo> bannerVoList;
    /**
     * 轮播图位置
     */
    private int position = 0;
    /**
     * banner高度
     */
    private int height;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private Context context;
    private void initView(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_indicator, this);
        height = G.INSTANCE.dp2px(context, 250);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        setLayoutParams(layoutParams);
        rv_dos = (RecyclerView) findViewById(R.id.rv_dos);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dosAdapter = new BannerDosAdapter(position, 5);
        rv_dos.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_dos.setAdapter(dosAdapter);
        initList();

    }

    /**
     * 初始化轮播图信息
     */
    public void initList() {
        bannerVoList = new ArrayList<>();
        indicatorViews = new ArrayList<>();
        contentAdapter = new IndicatorContentAdapter(indicatorViews);
        viewPager.setAdapter(contentAdapter);
        contentAdapter.setOnItemClickListener(this);
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(this);
    }

    /**
     * 设置轮播图
     *
     * @param bannerVoList banner信息列表
     */
    public void setBannerVoList(List<LatestStoryVo.TopStoriesVo> bannerVoList, Context context) {
        this.bannerVoList.clear();
        this.bannerVoList.addAll(bannerVoList);
        change(context);

    }

    private void change(Context context) {
        indicatorViews.clear();
        for (int i = 0; i < bannerVoList.size(); i++) {
            IndicatorView view = new IndicatorView(context);
            view.setBannerVo(bannerVoList.get(i));
            indicatorViews.add(view);
        }
        if (contentAdapter != null) {
            contentAdapter.notifyDataSetChanged();
        }
        dosAdapter.iniData(0,indicatorViews.size());
    }
    /**
     * 当个轮播图
     *
     * @param detailVo
     */
    public void setSingleBanner(ThemeDetailVo detailVo, Context context) {
        this.bannerVoList.clear();
        LatestStoryVo.TopStoriesVo topStoriesVo = new LatestStoryVo.TopStoriesVo();
        topStoriesVo.setImage(detailVo.getBackground());
        topStoriesVo.setTitle(detailVo.getDescription());
        this.bannerVoList.add(topStoriesVo);
        change(context);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
        dosAdapter.iniData(position, bannerVoList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(context, StoryDetailActivity.class);
        intent.putExtra("storyId",bannerVoList.get(position).getId());
        context.startActivity(intent);
    }

    private class IndicatorContentAdapter extends PagerAdapter {
        private List<IndicatorView> indicatorViews;
        private int mChildCount = 0;
        private OnItemClickListener onItemClickListener;
        public IndicatorContentAdapter(List<IndicatorView> indicatorViews) {
            this.indicatorViews = indicatorViews;
        }

        @Override
        public int getCount() {
            return indicatorViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            final IndicatorView indicatorView  = indicatorViews.get(position);
            indicatorView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null&&indicatorViews.size()!=1){
                        onItemClickListener.onClick(v,position);
                    }
                }
            });
            container.addView(indicatorView);
            return indicatorView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(indicatorViews.get(position));
        }
        @Override
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }
        @Override
        public int getItemPosition(Object object) {
            if (mChildCount > 0) {
                mChildCount--;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
    }

}
