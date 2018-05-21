package com.focustech.tobuy.ui.personcenter.Help.search;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.focustech.tobuy.ui.personcenter.activity.search.SearchActivity;
import com.focustech.tobuy.ui.personcenter.adapter.search.SearchRecyclerAdapter;

/**
 * Created by Administrator on 2018/4/29.
 */

public class SearchScrollHelper {

    private SearchActivity searchActivity;
    private SearchRecyclerAdapter searchRecyclerAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public SearchScrollHelper(RecyclerView recyclerView, SearchActivity searchActivity, SearchRecyclerAdapter searchRecyclerAdapter, SwipeRefreshLayout swipeRefreshLayout) {
        this.recyclerView = recyclerView;
        this.searchActivity = searchActivity;
        this.searchRecyclerAdapter = searchRecyclerAdapter;
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
                    /*if (searchRecyclerAdapter.DATA_CHANGE_FLAG){
                        searchRecyclerAdapter.updata();
                    }*/
                }
            }

        });
    }

}
