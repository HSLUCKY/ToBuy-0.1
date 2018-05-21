package com.focustech.tobuy.biz.personcenter.publish;

import com.focustech.tobuy.bean.service.card.PublishCardResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.publish.view.IPublishView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/5/2.
 */

public class PublishPresenter extends BasePresenter<IPublishView> {

    /**
     * @param publishCardResp   发布帖子实体
     */
    public void publishCard(PublishCardResp publishCardResp){
        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT+"?view=publish&method=publishCard", getName(), new ITRequestResult<PublishCardResp>() {

            @Override
            public void onSuccessful(PublishCardResp entity) {
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, null);
            }

            @Override
            public void onCompleted() {

            }
        }, PublishCardResp.class, new Param("publishCardResp", new Gson().toJson(publishCardResp)));
    }



}
