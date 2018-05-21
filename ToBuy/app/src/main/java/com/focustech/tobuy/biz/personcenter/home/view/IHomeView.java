package com.focustech.tobuy.biz.personcenter.home.view;

import com.focustech.tobuy.bean.service.goods.GetGoodsDetailResp;
import com.focustech.tobuy.bean.service.goods.GetGoodsResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.biz.base.IMvpView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/1.
 */

public interface IHomeView extends IMvpView {

    public abstract void preLoadGoods(GetGoodsResp goodsList);
    public abstract void eachLoadGoods(GetGoodsResp goodsList, ArrayList<String> images);
    public abstract void sortedLoadGoods(GetGoodsResp goodsList);
    public abstract void detailLoadGoods(GetGoodsDetailResp detailGoods);
    public abstract void sendGoodsMessage(SendMessageResp sendMessage);

}
