package com.focustech.tobuy.ui.personcenter.activity.community;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.card.GetSimpleCardListResp;
import com.focustech.tobuy.biz.personcenter.community.CommunitiyPresenter;
import com.focustech.tobuy.biz.personcenter.community.view.ICommunityView;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.Help.community.BuyScrollHelper;
import com.focustech.tobuy.ui.personcenter.Help.community.CommunityScrollHelper;
import com.focustech.tobuy.ui.personcenter.Help.community.HelpScrollHelper;
import com.focustech.tobuy.ui.personcenter.Help.community.RentScrollHelper;
import com.focustech.tobuy.ui.personcenter.adapter.community.session.BuyRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.adapter.community.session.CommunityRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.adapter.community.session.HelpRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.adapter.community.session.RentRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.community.SessionComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 社区
 * parts[0].setText("购物");
 * parts[1].setText("租借");
 * parts[2].setText("帮帮");
 * parts[3].setText("社区");
 */

public class CommunityActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, ICommunityView {

    int screenWidth = EBApplication.screenSize.get("SCREEN_WIDTH");
    int screenHeight = EBApplication.screenSize.get("SCREEN_HEIGHT");


    private HorizontalScrollView horizontalScrollView;

    //Buy
    private SwipeRefreshLayout buySwipeRefreshLayout;
    private RecyclerView buyRecyclerView;
    private BuyScrollHelper buyScrollHelper;
    private BuyRecyclerAdapter buyRecyclerAdapter;

    //Rent
    private SwipeRefreshLayout rentSwipeRefreshLayout;
    private RecyclerView rentRecyclerView;
    private RentScrollHelper rentScrollHelper;
    private RentRecyclerAdapter rentRecyclerAdapter;

    //Help
    private SwipeRefreshLayout helpSwipeRefreshLayout;
    private RecyclerView helpRecyclerView;
    private HelpScrollHelper helpScrollHelper;
    private HelpRecyclerAdapter helpRecyclerAdapter;


    //Community
    private SwipeRefreshLayout communitySwipeRefreshLayout;
    private RecyclerView communityRecyclerView;
    private CommunityScrollHelper communityScrollHelper;
    private CommunityRecyclerAdapter communityRecyclerAdapter;

    private LinearLayout[] guideLLs;
    private LinearLayout guideLL1;
    private LinearLayout guideLL2;
    private LinearLayout guideLL3;
    private LinearLayout guideLL4;

    private int sorted = 0;

    public ArrayList<Integer>[] rulers = new ArrayList[4];

    public CommunitiyPresenter communitiyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.community_activity);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initData() {
        presenter = communitiyPresenter = new CommunitiyPresenter();
        presenter.attachView(this);

        for (int i = 0; i < rulers.length; i++){
            rulers[i] = new ArrayList<>();
            rulers[i].add(1);
            rulers[i].add(1);
        }
    }

    @Override
    public void initViews() {
        initCommunityPart();
        super.initViews();

        ACTIVITY_DATA = ((ArrayList<Object>) EBApplication.ACTIVITY_CACHE.get(this.getClass().getName()));

        if (ACTIVITY_DATA == null){
            ACTIVITY_DATA = new ArrayList<>();

        }

        if (ACTIVITY_DATA.size() < 4) {
            for (int i = 0; i < 4; i++){
                ACTIVITY_DATA.add(new Object());
            }
            communitiyPresenter.preLoadCards(0);
            communitiyPresenter.preLoadCards(1);
            communitiyPresenter.preLoadCards(2);
            communitiyPresenter.preLoadCards(3);
           EBApplication.ACTIVITY_CACHE.put(this.getClass().getName(), ACTIVITY_DATA);
        }else {
            buyRecyclerAdapter.data = (GetSimpleCardListResp) ACTIVITY_DATA.get(0);
            rentRecyclerAdapter.data = (GetSimpleCardListResp) ACTIVITY_DATA.get(1);
            helpRecyclerAdapter.data = (GetSimpleCardListResp) ACTIVITY_DATA.get(2);
            communityRecyclerAdapter.data = (GetSimpleCardListResp) ACTIVITY_DATA.get(3);
            initRuler((GetSimpleCardListResp) ACTIVITY_DATA.get(0), 0);
            initRuler((GetSimpleCardListResp) ACTIVITY_DATA.get(1), 1);
            initRuler((GetSimpleCardListResp) ACTIVITY_DATA.get(2), 2);
            initRuler((GetSimpleCardListResp) ACTIVITY_DATA.get(3), 3);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initListeners() {
        buySwipeRefreshLayout.setOnRefreshListener(this);
        buySwipeRefreshLayout.requestDisallowInterceptTouchEvent(true);
        rentSwipeRefreshLayout.setOnRefreshListener(this);
        rentSwipeRefreshLayout.requestDisallowInterceptTouchEvent(true);
        helpSwipeRefreshLayout.setOnRefreshListener(this);
        helpSwipeRefreshLayout.requestDisallowInterceptTouchEvent(true);
        communitySwipeRefreshLayout.setOnRefreshListener(this);
        communitySwipeRefreshLayout.requestDisallowInterceptTouchEvent(true);
        horizontalScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int x = scrollX;
                int mark = 0;
                if (x <= screenWidth * (2 / 3)) {
                    mark = 0;
                } else if (x > screenWidth * (2 / 3) && x <= screenWidth + screenWidth * (2 / 3)) {
                    mark = 1;
                } else if (x > screenWidth + screenWidth * (2 / 3) && x < 2 * screenWidth + screenWidth * (2 / 3)) {
                    mark = 2;
                } else {
                    mark = 3;
                }
                for (int i = 0; i < guideLLs.length; i++) {
                    if (i != mark) {
                        guideLLs[i].setBackgroundColor(Color.WHITE);
                    } else {
                        guideLLs[i].setBackgroundColor(0xFFFF9999);
                    }
                }
            }
        });
        horizontalScrollView.requestDisallowInterceptTouchEvent(false);

        guideLL1.setOnClickListener(this);
        guideLL2.setOnClickListener(this);
        guideLL3.setOnClickListener(this);
        guideLL4.setOnClickListener(this);


        super.initListeners();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                if (SessionComponent.sorts.keySet().contains(v)) {
                    initSwitch(v);
                }
                if (SessionComponent.simpleCardLayoutHashMap.keySet().contains(v)) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("cardId", SessionComponent.simpleCardLayoutHashMap.get(v).getCardId());
                    bundle.putInt("type", SessionComponent.simpleCardLayoutHashMap.get(v).getType());
                    startActivity(CardActivity.class, bundle);
                }
                childLayoutOnClick(v);
        }

        super.onClick(v);
    }
    public void childLayoutOnClick(View v) {
        boolean open = false;
        switch (v.getId()) {
            case R.id.guideLL1:
                open = true;
                horizontalScrollView.scrollTo(0, 0);
                break;
            case R.id.guideLL2:
                open = true;
                horizontalScrollView.scrollTo(screenWidth, 0);
                break;
            case R.id.guideLL3:
                open = true;
                horizontalScrollView.scrollTo(screenWidth * 2, 0);
                break;
            case R.id.guideLL4:
                open = true;
                horizontalScrollView.scrollTo(screenWidth * 3, 0);
                break;
        }
        if (open) {
            for (int i = 0; i < guideLLs.length; i++) {
                if (guideLLs[i] != v) {
                    guideLLs[i].setBackgroundColor(Color.WHITE);
                } else {
                    guideLLs[i].setBackgroundColor(0xFFFF9999);
                }
            }
            open = false;
        }
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
    public void showLoading() {

    }
    @Override
    public void hideLoading() {

    }
    public void initCommunityPart() {

        guideLL1 = findViewById(R.id.guideLL1);
        guideLL2 = findViewById(R.id.guideLL2);
        guideLL3 = findViewById(R.id.guideLL3);
        guideLL4 = findViewById(R.id.guideLL4);
        guideLLs = new LinearLayout[]{guideLL1, guideLL2, guideLL3, guideLL4};
        guideLLs[0].setBackgroundColor(0xFF9999);

        horizontalScrollView = findViewById(R.id.communityHSV);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(EBApplication.screenSize.get("SCREEN_WIDTH"), ViewGroup.LayoutParams.WRAP_CONTENT);
        buySwipeRefreshLayout = findViewById(R.id.sessionFlashSRFirst);
        buySwipeRefreshLayout.setLayoutParams(layoutParams);
        buySwipeRefreshLayout.setPadding(10, 20, 10, 20);
        rentSwipeRefreshLayout = findViewById(R.id.sessionFlashSRSecond);
        rentSwipeRefreshLayout.setLayoutParams(layoutParams);
        rentSwipeRefreshLayout.setPadding(10, 20, 10, 20);
        helpSwipeRefreshLayout = findViewById(R.id.sessionFlashSRThird);
        helpSwipeRefreshLayout.setLayoutParams(layoutParams);
        helpSwipeRefreshLayout.setPadding(10, 20, 10, 20);
        communitySwipeRefreshLayout = findViewById(R.id.sessionFlashSRFour);
        communitySwipeRefreshLayout.setLayoutParams(layoutParams);
        communitySwipeRefreshLayout.setPadding(10, 20, 10, 20);

        buyRecyclerView = findViewById(R.id.sessionPartRVFirst);
        rentRecyclerView = findViewById(R.id.sessionPartRVSecond);
        helpRecyclerView = findViewById(R.id.sessionPartRVThird);
        communityRecyclerView = findViewById(R.id.sessionPartRVFour);

        LinearLayoutManager buyLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager rentLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager helpLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager communityLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        buyRecyclerView.setLayoutManager(buyLinearLayoutManager);
        rentRecyclerView.setLayoutManager(rentLinearLayoutManager);
        helpRecyclerView.setLayoutManager(helpLinearLayoutManager);
        communityRecyclerView.setLayoutManager(communityLinearLayoutManager);

        buyRecyclerAdapter = new BuyRecyclerAdapter(this);
        buyRecyclerView.setAdapter(buyRecyclerAdapter);
        rentRecyclerAdapter = new RentRecyclerAdapter(this);
        rentRecyclerView.setAdapter(rentRecyclerAdapter);
        helpRecyclerAdapter = new HelpRecyclerAdapter(this);
        helpRecyclerView.setAdapter(helpRecyclerAdapter);
        communityRecyclerAdapter = new CommunityRecyclerAdapter(this);
        communityRecyclerView.setAdapter(communityRecyclerAdapter);

        buyScrollHelper = new BuyScrollHelper(buyRecyclerAdapter, this, buyRecyclerView, buySwipeRefreshLayout);
        rentScrollHelper = new RentScrollHelper(rentRecyclerAdapter, this, rentRecyclerView, rentSwipeRefreshLayout);
        helpScrollHelper = new HelpScrollHelper(helpRecyclerAdapter, this, helpRecyclerView, helpSwipeRefreshLayout);
        communityScrollHelper = new CommunityScrollHelper(communityRecyclerAdapter, this, communityRecyclerView, communitySwipeRefreshLayout);
    }
    public void initSwitch(View v) {
        int key = SessionComponent.sorts.get(v);
        sorted = key;
        Iterator<TextView> iterator = SessionComponent.sorts.keySet().iterator();
        if (key < 4) {
            while (iterator.hasNext()) {
                TextView tempTx = iterator.next();
                int temp = SessionComponent.sorts.get(tempTx);
                if (temp != key && temp < 4) {
                    tempTx.setTextColor(Color.BLACK);
                } else if (temp < 4) {
                    tempTx.setTextColor(0xFFFF9999);
                }
            }
        } else if (key >= 4 && key < 8) {
            while (iterator.hasNext()) {
                TextView tempTx = iterator.next();
                int temp = SessionComponent.sorts.get(tempTx);
                if (temp != key && temp >= 4 && temp < 8) {
                    tempTx.setTextColor(Color.BLACK);
                } else if (temp >= 4 && temp < 8) {
                    tempTx.setTextColor(0xFFFF9999);
                }
            }

        } else if (key >= 8 && key < 12) {
            while (iterator.hasNext()) {
                TextView tempTx = iterator.next();
                int temp = SessionComponent.sorts.get(tempTx);
                if (temp != key && temp >= 8 && temp < 12) {
                    tempTx.setTextColor(Color.BLACK);
                } else if (temp >= 8 && temp < 12) {
                    tempTx.setTextColor(0xFFFF9999);
                }
            }
        } else {
            while (iterator.hasNext()) {
                TextView tempTx = iterator.next();
                int temp = SessionComponent.sorts.get(tempTx);
                if (temp != key && temp >= 12) {
                    tempTx.setTextColor(Color.BLACK);
                } else if (temp >= 12) {
                    tempTx.setTextColor(0xFFFF9999);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        buySwipeRefreshLayout.setRefreshing(false);
        rentSwipeRefreshLayout.setRefreshing(false);
        helpSwipeRefreshLayout.setRefreshing(false);
        communitySwipeRefreshLayout.setRefreshing(false);

        super.onBackPressed();

    }
    @Override
    public void loadCards1(GetSimpleCardListResp entity) {
        buyRecyclerAdapter.data = entity;
        ACTIVITY_DATA.set(0, entity);
        initRuler(entity, 0);
        buyRecyclerAdapter.LOAD_DATA = false;
        buyRecyclerAdapter.updata();
        System.out.println("一号");
    }
    @Override
    public void loadCards2(GetSimpleCardListResp entity) {
        rentRecyclerAdapter.data = entity;
        ACTIVITY_DATA.set(1, entity);
        initRuler(entity, 1);
        rentRecyclerAdapter.LOAD_DATA = false;
        rentRecyclerAdapter.updata();
        System.out.println("二号");
    }
    @Override
    public void loadCards3(GetSimpleCardListResp entity) {
        helpRecyclerAdapter.data = entity;
        ACTIVITY_DATA.set(2, entity);
        initRuler(entity, 2);

        helpRecyclerAdapter.LOAD_DATA = false;
        helpRecyclerAdapter.updata();
        System.out.println("三号");
    }
    @Override
    public void loadCards4(GetSimpleCardListResp entity) {
        communityRecyclerAdapter.data = entity;
        ACTIVITY_DATA.set(3, entity);
        initRuler(entity, 3);
        communityRecyclerAdapter.LOAD_DATA = false;
        communityRecyclerAdapter.updata();
        System.out.println("四号");
    }


    public void initRuler(GetSimpleCardListResp entity, int type){
        for (int i = 0; i < entity.getBaseUserInfos().size(); i++){
            for (int j = 0; j < entity.getCardsLists().get(i).size(); j++){
                rulers[type].add(1);
            }
        }
    }
}
