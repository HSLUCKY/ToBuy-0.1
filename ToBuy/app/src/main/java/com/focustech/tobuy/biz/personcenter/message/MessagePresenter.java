package com.focustech.tobuy.biz.personcenter.message;

import com.focustech.tobuy.bean.service.goods.SendGoodsResp;
import com.focustech.tobuy.bean.service.message.GetMessagesesResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.message.view.IMessageViewe;
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

public class MessagePresenter extends BasePresenter<IMessageViewe> {

    /**
     * 商品
     * @param id    接收方id
     * @param type  用于区分是哪张关联表的用户信息表还是其余的
     */
    public void getGoodstMessagesList(int id, int type){

        GetMessagesesResp getMessagesesResp = new GetMessagesesResp();
        getMessagesesResp.setId(id);
        getMessagesesResp.setType(type);

        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT+"?view=home&method=loadGoodsMessages", getName(), new ITRequestResult<GetMessagesesResp>() {
            @Override
            public void onSuccessful(GetMessagesesResp entity) {
                mvpView.loadMessages(entity);
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        }, GetMessagesesResp.class, new Param("getMessagesesRespJson", new Gson().toJson(getMessagesesResp)));
    }

    public void getCardtMessagesList(int id, int type){

        GetMessagesesResp getMessagesesResp = new GetMessagesesResp();
        getMessagesesResp.setId(id);
        getMessagesesResp.setType(type);

        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT+"?view=community&method=loadMessages", getName(), new ITRequestResult<GetMessagesesResp>() {
            @Override
            public void onSuccessful(GetMessagesesResp entity) {
                mvpView.loadMessages(entity);
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        }, GetMessagesesResp.class, new Param("getMessagesesRespJson", new Gson().toJson(getMessagesesResp)));
    }

    public void getUserMessagesList(int id, int type){

        GetMessagesesResp getMessagesesResp = new GetMessagesesResp();
        getMessagesesResp.setId(id);
        getMessagesesResp.setType(type);

        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT+"?view=message&method=loadUserMessages", getName(), new ITRequestResult<GetMessagesesResp>() {
            @Override
            public void onSuccessful(GetMessagesesResp entity) {
                mvpView.loadMessages(entity);
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        }, GetMessagesesResp.class, new Param("getMessagesesRespJson", new Gson().toJson(getMessagesesResp)));
    }

    public void getUserMessagesListByFrom(int mid, int tid){

        GetMessagesesResp getMessagesesResp = new GetMessagesesResp();
        getMessagesesResp.setId(mid);
        getMessagesesResp.setType(tid);

        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT+"?view=message&method=loadUsersInfoByFrom", getName(), new ITRequestResult<GetMessagesesResp>() {
            @Override
            public void onSuccessful(GetMessagesesResp entity) {
                mvpView.loadMessages(entity);
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        }, GetMessagesesResp.class, new Param("getMessagesesRespJson", new Gson().toJson(getMessagesesResp)));
    }







    /**
     * 发送消息
     * 用消息实体接收保存后的数据
     * 接收用于其他用户
     *
     * @param sendMessageResp
     */
    public void postMessage(SendMessageResp sendMessageResp){
    //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=message&method=postMessage", getName(), new ITRequestResult<SendMessageResp>() {
            @Override
            public void onSuccessful(SendMessageResp entity) {
                mvpView.onSuccess();
                mvpView.sendMessage(entity);
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, null);

            }

            @Override
            public void onCompleted() {

            }
        }, SendMessageResp.class, new Param("sendMessageResp", new Gson().toJson(sendMessageResp)));
    }



}
