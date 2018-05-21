package com.focustech.tobuy.biz.personcenter.message.view;

import com.focustech.tobuy.bean.service.message.GetMessagesesResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.biz.base.IMvpView;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface IMessageViewe extends IMvpView {
    public abstract void loadMessages(GetMessagesesResp getMessagesesResp);
    public abstract void sendMessage(SendMessageResp sendMessageResp);
}
