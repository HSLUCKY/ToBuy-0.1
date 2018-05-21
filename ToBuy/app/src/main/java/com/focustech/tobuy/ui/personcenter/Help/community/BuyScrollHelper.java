package com.focustech.tobuy.ui.personcenter.Help.community;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.focustech.tobuy.ui.personcenter.activity.community.CommunityActivity;
import com.focustech.tobuy.ui.personcenter.adapter.community.session.BuyRecyclerAdapter;

/**
 * Created by Administrator on 2018/4/22.
 */

public class BuyScrollHelper {

    private CommunityActivity communityActivity;
    private BuyRecyclerAdapter buyRecyclerAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public BuyScrollHelper(BuyRecyclerAdapter buyRecyclerAdapter, CommunityActivity communityActivity, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.buyRecyclerAdapter = buyRecyclerAdapter;
        this.communityActivity = communityActivity;
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
                    /*if (buyRecyclerAdapter.DATA_CHANGE_FLAG){
                        buyRecyclerAdapter.updata();
                    }*/
                }
            }

        });
    }

}
