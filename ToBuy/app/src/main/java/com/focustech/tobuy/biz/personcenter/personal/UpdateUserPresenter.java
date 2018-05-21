package com.focustech.tobuy.biz.personcenter.personal;

import com.focustech.tobuy.bean.service.personal.SendAllUserResp;
import com.focustech.tobuy.bean.service.personal.SendSomeUserResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.base.IMvpView;
import com.focustech.tobuy.biz.personcenter.personal.view.IUpdateUserView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/2.
 */

public class UpdateUserPresenter extends BasePresenter<IUpdateUserView> {

    /**
     * 更新用户所有信息
     * 保存的时候用的
     */
    public void updateAllInfo(SendAllUserResp sendAllUserResp){
        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT, getName(), new ITRequestResult<SendAllUserResp>() {
            @Override
            public void onSuccessful(SendAllUserResp entity) {
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

            @Override
            public void onCompleted() {

            }
        }, SendAllUserResp.class, new Param("sendAllUserResp", new Gson().toJson(sendAllUserResp)));
    }

    /**
     * 更新一些用户信息
     */
    public void updateSomeInfo(SendSomeUserResp sendSomeUserResp){
        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT, getName(), new ITRequestResult<SendAllUserResp>() {
            @Override
            public void onSuccessful(SendAllUserResp entity) {
                mvpView.onSuccess();
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

            @Override
            public void onCompleted() {

            }
        }, SendAllUserResp.class, new Param("sendSomeUserResp", new Gson().toJson(sendSomeUserResp)));

    }
}
