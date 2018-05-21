package com.focustech.tobuy.ui.personcenter.Help.community;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.focustech.tobuy.ui.personcenter.activity.community.CommunityActivity;
import com.focustech.tobuy.ui.personcenter.adapter.community.session.HelpRecyclerAdapter;

/**
 * Created by Administrator on 2018/4/22.
 */

public class HelpScrollHelper {

    private CommunityActivity communityActivity;
    private RecyclerView recyclerView;
    private HelpRecyclerAdapter helpRecyclerAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public HelpScrollHelper(HelpRecyclerAdapter helpRecyclerAdapter, CommunityActivity communityActivity, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.helpRecyclerAdapter = helpRecyclerAdapter;
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
                  /*  if (helpRecyclerAdapter.DATA_CHANGE_FLAG){
                        helpRecyclerAdapter.updata();
                    }*/
                }
            }

        });
    }

}
