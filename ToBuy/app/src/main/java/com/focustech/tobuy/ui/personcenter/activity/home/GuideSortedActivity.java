package com.focustech.tobuy.ui.personcenter.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.goods.GetGoodsDetailResp;
import com.focustech.tobuy.bean.service.goods.GetGoodsResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.biz.personcenter.home.view.IHomeView;
import com.focustech.tobuy.biz.personcenter.search.SearchPresenter;
import com.focustech.tobuy.biz.personcenter.search.view.ISearchView;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.base.BaseComponent;
import com.focustech.tobuy.ui.personcenter.Help.home.GuideSortedScrollHelper;
import com.focustech.tobuy.ui.personcenter.adapter.home.GuideSortedRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.home.GuideSortedComponent;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 */

public class GuideSortedActivity extends BaseActivity implements IHomeView, ISearchView {

    public LinearLayout commonBackRoot;
    public ImageView messageBack;

    public SwipeRefreshLayout sortedSR;
    public RecyclerView sortedRV;

    public static boolean priceSorted = true;

    private int sorted = 0;
    private TextView[] sortedTxs;

    public SearchPresenter searchPresenter;

    public GuideSortedRecyclerAdapter guideSortedRecyclerAdapter;

    /**
     * 容器尺子
     */
    public ArrayList<Integer> ruler = new ArrayList<>();
    /**
     * 商品
     */
    public ArrayList<GoodsTable> datas = new ArrayList<>();
    /**
     * 图片
     */
    public ArrayList<ArrayList<byte[]>> imageses = new ArrayList<>();

    /**
     * 关键词
     */
    public String guideKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.guidesorted_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

        Bundle bundle = this.getIntent().getExtras();
        guideKey = bundle.getString("guidesName");

        if (searchPresenter == null){
            presenter = searchPresenter = new SearchPresenter();
            presenter.attachView(this);
        }
        if(guideKey != null && guideKey.length() != 0){
            searchPresenter.search(guideKey);
        }
    }

    @Override
    public void initViews() {
        initSortedPage();
    }
    @Override
    public void initListeners() {
        messageBack.setOnClickListener(this);
        for (int i = 0; i < sortedTxs.length; i++){
            sortedTxs[i].setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messageBack:
                onBackPressed();
                break;
            default:
                if (GuideSortedComponent.simpleGoods.keySet().contains(v)) {
                    Bundle bundle = new Bundle();
                    bundleGoodsDetail(bundle, v);
                    startActivity(GoodsDetailActivity.class, bundle);
                }
                switchSorted(v);
        }
    }

    public void bundleGoodsDetail(Bundle bundle, View v){
        int goodsId =  GuideSortedComponent.simpleGoods.get(v).getCardId();
        bundle.putInt("goodsId", goodsId);
        bundle.putInt("from", 1);
        for (int i = 0; i < datas.size(); i++){
            if (datas.get(i).getId() == goodsId){
                bundle.putSerializable("datas", datas.get(i));
                bundle.putSerializable("images", imageses.get(i));
            }
        }


    }

    @Override
    public void loadGoodsByKey(GetGoodsResp getGoodsResp) {

        datas = getGoodsResp.getGoodsTable();
        imageses = getGoodsResp.getImages();
        for (int i = 0; i < datas.size(); i++){
            ruler.add(i);
        }
        guideSortedRecyclerAdapter.LOAD_DATA = false;
        guideSortedRecyclerAdapter.updata();
    }

    /**
     * 备用
     */
    //=============================================
    public void clearData(){

        ruler.clear();
        datas.clear();
        imageses.clear();
        guideSortedRecyclerAdapter.LOAD_DATA = false;
        guideSortedRecyclerAdapter.updata();

    }

    public boolean checkInput(){

        if (guideKey.trim().length() == 0){
            ToastUtil.makeText(this, "关键词不能为空");
            return false;
        }
        return true;
    }
//=============================================

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void initSortedPage() {

        sortedTxs = new TextView[4];
        sortedTxs[0] = findViewById(R.id.sortedTx1);
        sortedTxs[1] = findViewById(R.id.sortedTx2);
        sortedTxs[2] = findViewById(R.id.sortedTx3);
        sortedTxs[3] = findViewById(R.id.sortedTx4);

        commonBackRoot = findViewById(R.id.commonBackRoot);
        messageBack = findViewById(R.id.messageBack);

        sortedSR = findViewById(R.id.sortedSR);
        sortedRV = findViewById(R.id.sortedRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sortedRV.setLayoutManager(linearLayoutManager);
        guideSortedRecyclerAdapter = new GuideSortedRecyclerAdapter(this);
        sortedRV.setAdapter(guideSortedRecyclerAdapter);

        GuideSortedScrollHelper guideSortedScrollHelper = new GuideSortedScrollHelper(guideSortedRecyclerAdapter, sortedRV, sortedSR, this);

    }
    public int switchSorted(View v){
        for (int i = 0; i < sortedTxs.length; i++){
            if (sortedTxs[i].equals(v)){
                sorted = i;
                sortedTxs[i].setTextColor(0xFFFF9999);
            }else {
                sortedTxs[i].setTextColor(Color.BLACK);
            }
        }
        return sorted;
    }

    @Override
    public void preLoadGoods(GetGoodsResp goodsList) {

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

}
