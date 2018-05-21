package com.focustech.tobuy.ui.personcenter.activity.search;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.goods.GetGoodsResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.biz.personcenter.search.SearchPresenter;
import com.focustech.tobuy.biz.personcenter.search.view.ISearchView;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.home.GoodsDetailActivity;
import com.focustech.tobuy.ui.personcenter.adapter.search.SearchRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.home.GuideSortedComponent;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/15.
 */

public class SearchActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, ISearchView {
    private MyTextView[] searchSorteds = new MyTextView[4];

    private SwipeRefreshLayout searchSR;
    private RecyclerView searchRV;
    private SearchRecyclerAdapter searchRecyclerAdapter;

    private int sorted = 0;

    /**
     * 返回
     */
    private LinearLayout searchBack;
    /**
     * 查找
     */
    private LinearLayout searchAction;
    /**
     * 输入框
     */
    private EditText searchKey;
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

    private SearchPresenter searchPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.search_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        if (searchPresenter == null){
            presenter = searchPresenter = new SearchPresenter();
            presenter.attachView(this);
        }
    }

    @Override
    public void initViews() {
        initSearchPage();
    }

    @Override
    public void initListeners() {
        initEditDoneListener();
        searchBack.setOnClickListener(this);
        searchAction.setOnClickListener(this);
        for (int i = 0; i < searchSorteds.length; i++){
            searchSorteds[i].setOnClickListener(this);
        }
        searchSR.setOnRefreshListener(this);

    }
    public void initSearchPage() {
        searchBack = findViewById(R.id.searchBack);
        searchKey = findViewById(R.id.searchKey);
        searchAction = findViewById(R.id.searchAction);

        searchSorteds[0] = findViewById(R.id.searchSorted1);
        searchSorteds[1] = findViewById(R.id.searchSorted2);
        searchSorteds[2] = findViewById(R.id.searchSorted3);
        searchSorteds[3] = findViewById(R.id.searchSorted4);

        searchSR = findViewById(R.id.searchSR);
        searchRV = findViewById(R.id.searchRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        searchRV.setLayoutManager(linearLayoutManager);
        searchRecyclerAdapter = new SearchRecyclerAdapter(this);
        searchRV.setAdapter(searchRecyclerAdapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchBack:
                super.onBackPressed();
                break;
            case R.id.searchAction:
                doSearch();
                break;
            default:
                onSortedListener(v);
                if (GuideSortedComponent.simpleGoods.keySet().contains(v)){
                    Bundle bundle = new Bundle();
                    bundleGoodsDetail(bundle,v);
                    startActivity(GoodsDetailActivity.class, bundle);
                }
        }
    }
    /**
     * 绑定商品数据传送到具体商品页面
     * @param bundle
     * @param v
     */
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
        clearData();

        datas = getGoodsResp.getGoodsTable();
        imageses = getGoodsResp.getImages();

        for (int i = 0; i < datas.size(); i++){
            ruler.add(i);
        }
        searchRecyclerAdapter.LOAD_DATA = false;
        searchRecyclerAdapter.updata();
    }
    public void doSearch(){
        if (!checkInput()){
            return;
        }
        searchPresenter.search(searchKey.getText().toString().trim());
    }
    public void clearData(){
        ruler.clear();
        datas.clear();
        imageses.clear();
        searchRecyclerAdapter.LOAD_DATA = false;
        searchRecyclerAdapter.updata();

    }

    public boolean checkInput(){
        if (searchKey.getText().toString().trim().length() == 0){
            ToastUtil.makeText(this, "关键词不能为空");
            return false;
        }
        return true;
    }



    public void initEditDoneListener(){

        searchKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        doSearch();
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

    }

    public void onSortedListener(View v){

        for (int i = 0; i < searchSorteds.length; i++){
            if (searchSorteds[i].equals(v)){
                sorted = i;
                searchSorteds[i].setTextColor(0xFFFF9999);
            }else {
                searchSorteds[i].setTextColor(Color.BLACK);
            }
        }
    }
    @Override
    public void onRefresh() {

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
