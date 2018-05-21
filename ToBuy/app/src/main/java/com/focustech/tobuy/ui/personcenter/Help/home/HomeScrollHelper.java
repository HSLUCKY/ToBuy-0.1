package com.focustech.tobuy.ui.personcenter.Help.home;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.focustech.tobuy.ui.personcenter.activity.home.HomeActivity;
import com.focustech.tobuy.ui.personcenter.adapter.home.HomeRecyclerAdapter;


/**
 * Created by Administrator on 2018/3/22.
 */

public class HomeScrollHelper {

    private RecyclerView mRecyclerView;
    private HomeRecyclerAdapter mRecyclerBaseAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeActivity mHomeActivity;

    private boolean flag = false;

    public HomeScrollHelper(RecyclerView recyclerView, HomeRecyclerAdapter recyclerBaseAdapter, SwipeRefreshLayout swipeRefreshLayout, HomeActivity homeActivity) {

        this.mRecyclerView = recyclerView;
        this.mRecyclerBaseAdapter = recyclerBaseAdapter;
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.mHomeActivity = homeActivity;

        setScrollEvent();
    }

    private void setScrollEvent() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //向上滚动
                if (dy > 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mSwipeRefreshLayout.isRefreshing()){
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                            if (mHomeActivity.getTopTitle().getVisibility() == View.VISIBLE && flag) {
                                mHomeActivity.getTopTitle().setVisibility(View.GONE);
                                mHomeActivity.getFootTitle().setVisibility(View.GONE);
                                flag = false;
                            }
                        }
                    }, 10);

                    /**
                     * 上拉加载
                     * 此处ItemCount更新需要用服务器加载数据决定
                     * 通过服务器加载的数据动态改变RecyclerList更新时机
                     */
                   /* if (HomeRecyclerAdapter.DATA_CHANGE_FLAG){
                        mRecyclerBaseAdapter.updata();
                    }*/

                } else if (dy < 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mHomeActivity.getTopTitle().getVisibility() == View.GONE) {
                                mHomeActivity.getTopTitle().setVisibility(View.VISIBLE);
                                mHomeActivity.getFootTitle().setVisibility(View.VISIBLE);
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