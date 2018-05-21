package com.focustech.tobuy.biz.personcenter.personal;

import com.focustech.tobuy.bean.service.goods.SendGoodsResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.personal.view.IPublishGoodsView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;
import com.google.gson.Gson;

/**
 * 用户发布商品
 * 建立用户商品关联
 */

public class PublishGoodsPresenter extends BasePresenter<IPublishGoodsView> {

    /**
     * 发布商品
     * sendGoodsResp   用户发布商品的视图
     * 需要商品用户关联
     */
    public void publishGoods(SendGoodsResp sendGoodsResp) {

        mvpView.showLoading();
        OkHttpManager okHttpManager = BridgeFactory.getBridge(Bridges.HTTP);
        okHttpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=personal&method=publishGoods", getName(), new ITRequestResult<SendGoodsResp>() {
            @Override
            public void onSuccessful(SendGoodsResp entity) {
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

            @Override
            public void onCompleted() {

            }
        }, SendGoodsResp.class, new Param("sendGoodsResp", new Gson().toJson(sendGoodsResp)));

    }
}
