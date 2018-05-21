package com.focustech.tobuy.ui.personcenter.Help.message;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.focustech.tobuy.ui.personcenter.activity.message.MessageActivity;
import com.focustech.tobuy.ui.personcenter.adapter.message.MessageRecyclerAdapter;


/**
 * Created by Administrator on 2018/3/22.
 */

public class MessageScrollHelper {

    private RecyclerView mRecyclerView;
    private MessageRecyclerAdapter messageRecyclerBaseAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MessageActivity messageActivity;

    private boolean flag = false;

    public MessageScrollHelper(RecyclerView recyclerView, MessageRecyclerAdapter messageRecyclerBaseAdapter, SwipeRefreshLayout swipeRefreshLayout, MessageActivity messageActivity) {

        this.mRecyclerView = recyclerView;
        this.messageRecyclerBaseAdapter = messageRecyclerBaseAdapter;
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.messageActivity = messageActivity;

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
                            if (messageRecyclerBaseAdapter.getItemCount() == 0 && !mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(true);
                            }
                        }
                    }, 10);
                }
                /**
                 * 此处ItemCount更新需要用服务器加载数据决定
                 * 通过服务器加载的数据动态改变RecyclerList更新时机
                */
                /*if (MessageRecyclerAdapter.DATA_CHANGE_FLAG  || messageRecyclerBaseAdapter.getPictures().size() - 1 > MessageRecyclerAdapter.now){
                    messageRecyclerBaseAdapter.updata();
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