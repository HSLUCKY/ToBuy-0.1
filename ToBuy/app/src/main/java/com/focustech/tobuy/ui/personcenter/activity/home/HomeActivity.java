package com.focustech.tobuy.ui.personcenter.activity.home;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.goods.GetGoodsDetailResp;
import com.focustech.tobuy.bean.service.goods.GetGoodsResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.bean.service.resource.ResourceResp;
import com.focustech.tobuy.bean.service.resource.UserHeadResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.personcenter.home.HomePresenter;
import com.focustech.tobuy.biz.personcenter.home.view.IHomeView;
import com.focustech.tobuy.biz.personcenter.resource.ResourcePresenter;
import com.focustech.tobuy.biz.personcenter.resource.view.IResourceView;
import com.focustech.tobuy.bridge.cache.sharePref.EBSharedPrefUser;
import com.focustech.tobuy.capabilities.cache.FileUtil;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.constant.URLUtil;
import com.focustech.tobuy.ui.personcenter.Help.home.HomeScrollHelper;
import com.focustech.tobuy.ui.personcenter.adapter.home.HomeRecyclerAdapter;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.component.home.GoodsComponent;
import com.focustech.tobuy.ui.personcenter.component.home.GuideComponent;
import com.focustech.tobuy.util.DensityUtil;
import com.focustech.tobuy.util.ObjectTo;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static android.support.v4.widget.SwipeRefreshLayout.*;

/**
 * 主页   本窗口的消息本窗口处理，和本窗口无关的信息交给父窗口处理
 */
public class HomeActivity extends BaseActivity implements OnRefreshListener, IHomeView, IResourceView {

    //测试数据
    private ArrayList<GoodsTable> datas;
    public ArrayList<ArrayList<byte[]>> imageses;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    HomeRecyclerAdapter recyclerBaseAdapter;
    private HomeScrollHelper mHomeScrollHelper;

    public HomePresenter homePresenter;
    public ResourcePresenter resourcePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.home_activity);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initData() {
        if (user_head == null) {
            user_head = new ImageView(this);
        }
        if (homePresenter == null) {
            presenter = homePresenter = new HomePresenter();
            presenter.attachView(this);
        }
        if (resourcePresenter == null) {
            presenter = resourcePresenter = new ResourcePresenter();
            presenter.attachView(this);
        }

        datas = new ArrayList<>();
        imageses = new ArrayList<>();

        ACTIVITY_DATA = ((ArrayList<Object>) EBApplication.ACTIVITY_CACHE.get(this.getClass().getName()));

        if (ACTIVITY_DATA == null) {
            ACTIVITY_DATA = new ArrayList<>();
        }

        if (ACTIVITY_DATA.size() == 0) {
            homePresenter.preLoad();
            EBApplication.ACTIVITY_CACHE.put(this.getClass().getName(), ACTIVITY_DATA);
        } else {
            datas = (ArrayList<GoodsTable>) ACTIVITY_DATA.get(0);
            imageses = (ArrayList<ArrayList<byte[]>>) ACTIVITY_DATA.get(1);
        }
        resourcePresenter.loadUserHead(BaseActivity.userTable.getId());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initViews() {
        initHomePage();
        if (ACTIVITY_DATA.size() > 0) {
            this.recyclerBaseAdapter.updata();
        }
        super.initViews();
    }

    @Override
    public void initListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
        super.initListeners();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                if (GoodsComponent.simpleGoods.keySet().contains(v)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("goodsId", GoodsComponent.simpleGoods.get(v).goodsId);
                    bundle.putInt("from", 0);
                    startActivity(GoodsDetailActivity.class, bundle);
                }
                if (GuideComponent.guidesI.keySet().contains(v)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("guidesName", GuideComponent.guidesI.get(v).getText().getText().toString());
                    startActivity(GuideSortedActivity.class, bundle);
                }
                if (GuideComponent.advertiseI.keySet().contains(v)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("goodsId", GuideComponent.advertiseI.get(v));
                    bundle.putInt("from", 0);
                    startActivity(GoodsDetailActivity.class, bundle);
                }
        }


        super.onClick(v);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onEventMainThread(Event event) {

        super.onEventMainThread(event);
    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onBackPressed() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    /**
     * 初始化主界面布局
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initHomePage() {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.gank_swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.gank_recycler_view);

        //swipeRefreshLayout.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //瀑布流布局 第一个参数为列数    第二个表示布局方向
        // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //设置RecyclerView布局
        recyclerView.setLayoutManager(linearLayoutManager);


        //设置Adapter
        recyclerBaseAdapter = new HomeRecyclerAdapter(datas, this);
        recyclerView.setAdapter(recyclerBaseAdapter);
        //recyclerView.setNestedScrollingEnabled(false);
        /**
         * 对RecyclerView设置滚动监听事件
         */
        mHomeScrollHelper = new HomeScrollHelper(recyclerView, recyclerBaseAdapter, swipeRefreshLayout, this);
    }

    /**
     * =========================================================================================================================================
     * <p>
     * 数据库操作
     * <p>
     * =========================================================================================================================================
     */

    @Override
    public void preLoadGoods(GetGoodsResp goodsList) {
        for (GoodsTable goods : goodsList.getGoodsTable()) {
            if (goods != null) {
                datas.add(goods);
            }
        }
        imageses = goodsList.getImages();
        ACTIVITY_DATA.add(datas);
        ACTIVITY_DATA.add(imageses);


        recyclerBaseAdapter.LOAD_DATA = false;
        recyclerBaseAdapter.updata();
    }


    @Override
    public void eachLoadGoods(GetGoodsResp goodsList, ArrayList<String> images) {

    }

    @Override
    public void sortedLoadGoods(GetGoodsResp goodsList) {

    }

    @Override
    public void detailLoadGoods(GetGoodsDetailResp detailGoods) {

    }

    @Override
    public void sendGoodsMessage(SendMessageResp sendMessage) {

    }

    @Override
    public void loadResources(ResourceResp resourceResp) {

    }

    @Override
    public void loadHead(UserHeadResp userHeadResp) {
        byte[] head = userHeadResp.getHead();
        BaseActivity.user_head.setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));
        if (user_head != null) {
            account.setImageDrawable(user_head.getDrawable());
        } else {
            account = findViewById(R.id.head);
        }
    }
}
