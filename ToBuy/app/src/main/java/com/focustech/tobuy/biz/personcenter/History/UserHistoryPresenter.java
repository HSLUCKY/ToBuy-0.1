package com.focustech.tobuy.biz.personcenter.History;

import com.focustech.tobuy.bean.service.history.GetHistoryListResp;
import com.focustech.tobuy.bean.service.history.SendHistoryListResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.base.IMvpView;
import com.focustech.tobuy.biz.personcenter.History.view.IUserHistoryView;
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

public class UserHistoryPresenter extends BasePresenter<IUserHistoryView> {

    public void getHistoryList(int id) {
        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT, getName(), new ITRequestResult<GetHistoryListResp>() {

            @Override
            public void onSuccessful(GetHistoryListResp entity) {
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

            @Override
            public void onCompleted() {

            }
        }, GetHistoryListResp.class, new Param("id", id));
    }

    public void addSomeHistory(SendHistoryListResp sendHistoryListResp) {
        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT, getName(), new ITRequestResult<SendHistoryListResp>() {
            @Override
            public void onSuccessful(SendHistoryListResp entity) {
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

            @Override
            public void onCompleted() {

            }
        }, SendHistoryListResp.class, new Param("sendHistoryListResp", new Gson().toJson(sendHistoryListResp)));
    }

}
