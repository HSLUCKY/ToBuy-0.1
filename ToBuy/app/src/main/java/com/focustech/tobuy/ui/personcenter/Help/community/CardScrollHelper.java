package com.focustech.tobuy.ui.personcenter.Help.community;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.focustech.tobuy.ui.personcenter.activity.community.CardActivity;
import com.focustech.tobuy.ui.personcenter.adapter.community.CardRecyclerAdapter;

/**
 * Created by Administrator on 2018/4/26.
 */

public class CardScrollHelper {

    private CardActivity cardActivity;
    private CardRecyclerAdapter cardRecyclerAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public CardScrollHelper(CardActivity cardActivity, CardRecyclerAdapter cardRecyclerAdapter, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.cardActivity = cardActivity;
        this.cardRecyclerAdapter = cardRecyclerAdapter;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
        setScrollEvent();
    }
    public void setScrollEvent(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (swipeRefreshLayout.isRefreshing()){
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    }, 10);
                    /*if (cardRecyclerAdapter.DATA_CHANGE_FLAG){
                        cardRecyclerAdapter.updata();
                    }*/
                }
            }

        });
    }


}

