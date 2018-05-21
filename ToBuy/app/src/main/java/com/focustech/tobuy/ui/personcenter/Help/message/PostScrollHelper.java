package com.focustech.tobuy.ui.personcenter.Help.message;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.focustech.tobuy.ui.personcenter.activity.message.PostActivity;
import com.focustech.tobuy.ui.personcenter.adapter.message.PostRecyclerAdapter;


/**
 * Created by Administrator on 2018/3/22.
 */

public class PostScrollHelper {

    private RecyclerView mRecyclerView;
    private PostRecyclerAdapter postRecyclerBaseAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PostActivity postActivity;

    private boolean flag = false;

    public PostScrollHelper(RecyclerView recyclerView, PostRecyclerAdapter postRecyclerBaseAdapter, SwipeRefreshLayout swipeRefreshLayout, PostActivity postActivity) {

        this.mRecyclerView = recyclerView;
        this.postRecyclerBaseAdapter = postRecyclerBaseAdapter;
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.postActivity = postActivity;

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
                        }
                    }, 10);

                } else if (dy < 0) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (postRecyclerBaseAdapter.getItemCount() == 0 && !mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(true);
                            }
                        }
                    }, 10);
                }
                /**
                 * 此处ItemCount更新需要用服务器加载数据决定
                 * 通过服务器加载的数据动态改变RecyclerList更新时机
                */
                /*if (PostRecyclerAdapter.DATA_CHANGE_FLAG  || postRecyclerBaseAdapter.getPictures().size() - 1 > PostRecyclerAdapter.now){
                    postRecyclerBaseAdapter.updata();
                }*/
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