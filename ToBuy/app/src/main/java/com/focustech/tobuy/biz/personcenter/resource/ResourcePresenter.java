package com.focustech.tobuy.biz.personcenter.resource;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.service.resource.ResourceResp;
import com.focustech.tobuy.bean.service.resource.UserHeadResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.resource.view.IResourceView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/4.
 */

public class ResourcePresenter extends BasePresenter<IResourceView> {

    public void loadResource(ResourceResp resourceResp){
        OkHttpManager okHttpManager = BridgeFactory.getBridge(Bridges.HTTP);
        okHttpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT, getName(), new ITRequestResult<ResourceResp>() {

            @Override
            public void onSuccessful(ResourceResp entity) {
                mvpView.onSuccess();
                mvpView.loadResources(entity);
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

            @Override
            public void onCompleted() {

            }
        },ResourceResp.class, new Param("resourceResp", new Gson().toJson(resourceResp)));
    }

    public void loadUserHead(int id){
        UserHeadResp userHeadResp = new UserHeadResp();
        userHeadResp.setId(id);
        OkHttpManager okHttpManager = BridgeFactory.getBridge(Bridges.HTTP);
        okHttpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT+"?view=home&method=loadUserHead", getName(), new ITRequestResult<UserHeadResp>() {

            @Override
            public void onSuccessful(UserHeadResp entity) {
                mvpView.loadHead(entity);
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

            public void onCompleted() {

            }
        },UserHeadResp.class, new Param("userHeadResp", new Gson().toJson(userHeadResp)));
    }
}
