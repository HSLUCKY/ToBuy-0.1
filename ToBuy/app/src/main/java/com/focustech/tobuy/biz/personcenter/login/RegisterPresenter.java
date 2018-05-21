package com.focustech.tobuy.biz.personcenter.login;

import android.os.Handler;

import com.focustech.tobuy.bean.service.login.RegisterResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.login.view.IUserRegisterView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.bridge.security.SecurityManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.constant.URLUtil;

import de.greenrobot.event.EventBus;

/**
 * 用于注册的实体类
 */

public class RegisterPresenter extends BasePresenter<IUserRegisterView> {

    public void register(String userName, String password) {
        SecurityManager securityManager = BridgeFactory.getBridge(Bridges.SECURITY);
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);
        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT+"?view=account&method=register", getName(), new ITRequestResult<RegisterResp>() {

                    @Override
                    public void onSuccessful(RegisterResp entity) {
                        EventBus.getDefault().post(Event.REGISTER_SUCCESS);
                        mvpView.onSuccess();
                        mvpView.clearEdit();
                    }

                    @Override
                    public void onFailure(String errorMsg) {
                        mvpView.onError(errorMsg, null);
                    }

                    @Override
                    public void onCompleted() {
                    }

                }, RegisterResp.class, new Param("username", userName),
                new Param("password", securityManager.get32MD5Str(password)));
    }

}
