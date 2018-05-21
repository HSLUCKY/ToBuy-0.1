package com.focustech.tobuy.biz.personcenter.login;

import android.support.v4.widget.SwipeRefreshLayout;

import com.focustech.tobuy.bean.service.login.LoginResp;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.login.view.IUserLoginView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.cache.sharePref.EBSharedPrefManager;
import com.focustech.tobuy.bridge.cache.sharePref.EBSharedPrefUser;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.bridge.security.SecurityManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.constant.URLUtil;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import de.greenrobot.event.EventBus;

/**
 * <功能详细描述>
 * 继承自基表现类接收
 * 继承字基交互接口的具体接口
 * （此接口还提供了基接口中没有的自己特有的功能）
 */
public class LoginPresenter extends BasePresenter<IUserLoginView> {

    public LoginPresenter() {

    }

    public void login(String useName, String password) {
        //网络层
        mvpView.showLoading();
        SecurityManager securityManager = BridgeFactory.getBridge(Bridges.SECURITY);
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT+"?view=account&method=login", getName(),
                new ITRequestResult<LoginResp>() {
                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }

                    @Override
                    public void onSuccessful(LoginResp entity) {
                        mvpView.onSuccess();
                        mvpView.clearEditContent();
                        EventBus.getDefault().post(Event.LOGIN_SUCCESS);
                        EBSharedPrefManager manager = BridgeFactory.getBridge(
                                Bridges.SHARED_PREFERENCE);
                        BaseActivity.userTable = entity.getUserTable();
                    }
                    @Override
                    public void onFailure(String errorMsg) {
                        mvpView.onError(errorMsg, "用户名或密码错误");
                    }

                }, LoginResp.class, new Param("username", useName),
                new Param("password", securityManager.get32MD5Str(password)));

    }

}
