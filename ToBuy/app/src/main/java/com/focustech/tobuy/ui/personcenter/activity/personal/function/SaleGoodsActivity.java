package com.focustech.tobuy.ui.personcenter.activity.personal.function;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.goods.SendGoodsResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.biz.personcenter.personal.PublishGoodsPresenter;
import com.focustech.tobuy.biz.personcenter.personal.view.IPublishGoodsView;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.home.HomeActivity;
import com.focustech.tobuy.ui.personcenter.adapter.personal.SaleGoodsRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.personal.SaleGoodsComponent;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyCheckBoxView;
import com.focustech.tobuy.view.MyTextView;

import java.util.Date;

public class SaleGoodsActivity extends BaseActivity implements IPublishGoodsView{

    private RecyclerView saleRV;
    SaleGoodsRecyclerAdapter saleGoodsRecyclerAdapter;

    private MyTextView titleTx;
    private ImageView back;
    private ImageView publishinputPic;
    private ImageView publishinputSend;

    private EditText goodsTitle;

    private PublishGoodsPresenter publishGoodsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.salegoods_avtivity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

        if (publishGoodsPresenter == null){
            presenter = publishGoodsPresenter = new PublishGoodsPresenter();
            presenter.attachView(this);
        }

    }

    @Override
    public void initViews() {
        initSaleGoodsPage();
    }

    @Override
    public void initListeners() {
        publishinputPic.setOnClickListener(this);
        publishinputSend.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.publishinputPic:
                ToastUtil.makeText(this, "表情图片");
                break;
            case R.id.publishinputSend:
                publishGoods();
                break;
            case R.id.messageBack:
                super.onBackPressed();
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code) {

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

    public void initSaleGoodsPage(){
        saleRV = findViewById(R.id.saleRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        saleRV.setLayoutManager(linearLayoutManager);
        saleGoodsRecyclerAdapter = new SaleGoodsRecyclerAdapter(this);
        saleRV.setAdapter(saleGoodsRecyclerAdapter);

        titleTx = findViewById(R.id.friendTitle);
        titleTx.setText("发布商品");

        publishinputPic = findViewById(R.id.publishinputPic);
        publishinputSend = findViewById(R.id.publishinputSend);

        back = findViewById(R.id.messageBack);

        goodsTitle = findViewById(R.id.goodsTitle);

    }

    public void publishGoods(){
        SaleGoodsComponent.SaleGoods saleGoods = saleGoodsRecyclerAdapter.saleGoods;
        if (!checkInput(saleGoods)){
            return;
        }
        /**
         * 初始化响应体
         * 发布商品信息
         */
        SendGoodsResp sendGoodsResp = new SendGoodsResp();
        sendGoodsResp.setId(BaseActivity.userTable.getId());

        GoodsTable goodsTable = new GoodsTable();
        goodsTable.setTitle(goodsTitle.getText().toString());
        goodsTable.setPrice(Float.valueOf(saleGoods.getPriceEditText().getText().toString()));
        goodsTable.setTip(saleGoods.getTipEditText().getText().toString());
        goodsTable.setDate(new Date());
        goodsTable.setUserId(BaseActivity.userTable.getId());

        sendGoodsResp.setGoodsTable(goodsTable);

        publishGoodsPresenter.publishGoods(sendGoodsResp);

    }

    /**
     * 检验标题
     * 内容
     */
    public boolean checkInput(SaleGoodsComponent.SaleGoods saleGoods) {
        if (goodsTitle.getText().toString().length() == 0) {
            ToastUtil.makeText(this, "标题不能为空");
            return false;
        }

        if (saleGoods.getPtEditText().getText().toString().length() == 0) {
            ToastUtil.makeText(this, "内容不能为空");
            return false;
        }
        if (saleGoods.getPriceEditText().getText().toString().length() == 0){
            ToastUtil.makeText(this, "价格不能为空");
            return false;
        }

        return true;
    }


    @Override
    public void onSuccess() {
        /**
         * 发布成功清空操作
         * 跳转到商品界面重新加载
         */
        ToastUtil.makeText(this, "发布成功");
        EBApplication.ACTIVITY_CACHE.put(HomeActivity.class.getName(), null);
        startActivity(HomeActivity.class, null);

    }
}
