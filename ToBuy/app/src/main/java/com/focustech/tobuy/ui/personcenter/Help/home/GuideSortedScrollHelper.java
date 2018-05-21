package com.focustech.tobuy.ui.personcenter.Help.home;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.focustech.tobuy.ui.personcenter.activity.home.GuideSortedActivity;
import com.focustech.tobuy.ui.personcenter.adapter.home.GuideSortedRecyclerAdapter;

/**
 * Created by Administrator on 2018/4/26.
 */

public class GuideSortedScrollHelper {
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private GuideSortedRecyclerAdapter guideSortedRecyclerAdapter;

    private boolean flag = false;

    public GuideSortedScrollHelper(GuideSortedRecyclerAdapter guideSortedRecyclerAdapter, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout, Context context) {
        this.guideSortedRecyclerAdapter = guideSortedRecyclerAdapter;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.context = context;
        setScrollEvent();
    }

    private void setScrollEvent() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //向上滚动
                if (dy > 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (swipeRefreshLayout.isRefreshing()){
                                swipeRefreshLayout.setRefreshing(false);
                            }
                            if (((GuideSortedActivity)context).commonBackRoot.getVisibility() == View.VISIBLE && flag) {
                                ((GuideSortedActivity)context).commonBackRoot.setVisibility(View.GONE);
                                ((GuideSortedActivity)context).commonBackRoot.setVisibility(View.GONE);
                                flag = false;
                            }
                        }
                    }, 10);

                    /**
                     * 上拉加载
                     * 此处ItemCount更新需要用服务器加载数据决定
                     * 通过服务器加载的数据动态改变RecyclerList更新时机
                     */
                    if (guideSortedRecyclerAdapter.DATA_CHANGE_FLAG){
                        guideSortedRecyclerAdapter.updata();
                    }

                } else if (dy < 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (((GuideSortedActivity)context).commonBackRoot.getVisibility() == View.GONE) {
                                ((GuideSortedActivity)context).commonBackRoot.setVisibility(View.VISIBLE);
                                ((GuideSortedActivity)context).commonBackRoot.setVisibility(View.VISIBLE);
                            }
                        }
                    }, 10);
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                /**
                 * 避免布局闪烁
                 */
                if (newState == 2) {
                    flag = true;
                } else {
                    flag = false;
                }
            }

        });
    }


}
