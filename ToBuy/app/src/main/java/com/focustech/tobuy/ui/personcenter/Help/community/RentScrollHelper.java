package com.focustech.tobuy.ui.personcenter.Help.community;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.focustech.tobuy.ui.personcenter.activity.community.CommunityActivity;
import com.focustech.tobuy.ui.personcenter.adapter.community.session.RentRecyclerAdapter;

/**
 * Created by Administrator on 2018/4/22.
 */

public class RentScrollHelper {

    private CommunityActivity communityActivity;
    private RecyclerView recyclerView;
    private RentRecyclerAdapter rentRecyclerAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public RentScrollHelper(RentRecyclerAdapter rentRecyclerAdapter, CommunityActivity communityActivity, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.rentRecyclerAdapter = rentRecyclerAdapter;
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
                    /*if (rentRecyclerAdapter.DATA_CHANGE_FLAG){
                        rentRecyclerAdapter.updata();
                    }*/
                }
            }

        });
    }

}
