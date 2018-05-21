package com.focustech.tobuy.biz.personcenter.user;

import com.focustech.tobuy.bean.service.user.UserResp;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.user.view.IUserView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/12.
 */

public class UsersPresenter extends BasePresenter<IUserView> {

    public void loadUsersInfo(UserResp userResp){

        OkHttpManager okHttpManager = BridgeFactory.getBridge(Bridges.HTTP);
        okHttpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=message&method=loadUsersInfo", getName(), new ITRequestResult<UserResp>() {

            @Override
            public void onSuccessful(UserResp entity) {
                mvpView.loadUserInfo(entity);
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        },UserResp.class, new Param("userResp", new Gson().toJson(userResp)));

    }

    public void loadUserById(int id){
        OkHttpManager okHttpManager = BridgeFactory.getBridge(Bridges.HTTP);
        okHttpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=personal&method=loadUser", getName(), new ITRequestResult<UserResp>() {

            @Override
            public void onSuccessful(UserResp entity) {
                if (entity != null){
                    UserTable userTable = entity.getUsers().get(entity.getUid());
                    mvpView.loadUserById(userTable);
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        },UserResp.class, new Param("userId", new Gson().toJson(id)));

    }

    public void updateUserInfo(UserTable userTable){

        OkHttpManager okHttpManager = BridgeFactory.getBridge(Bridges.HTTP);
        okHttpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=account&method=updateUserInfo", getName(), new ITRequestResult<UserResp>() {
            @Override
            public void onSuccessful(UserResp entity) {
                UserTable userTable = entity.getUsers().get(entity.getUid());
                mvpView.updateUserInfo(userTable);
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        }, UserResp.class, new Param("userTable", new Gson().toJson(userTable)));


    }
}