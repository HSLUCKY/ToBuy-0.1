package com.focustech.tobuy.biz.personcenter.personal;

import com.focustech.tobuy.bean.service.history.GetHistoryListResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.personal.view.IGetHistoryView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.constant.URLUtil;

/**
 * Created by Administrator on 2018/5/2.
 */

public class GetHistoryPresenter extends BasePresenter<IGetHistoryView> {

    public void getHistoryList(GetHistoryListResp simpleHistoryListResp){

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
                mvpView.onError(errorMsg,"");
            }

            @Override
            public void onCompleted() {

            }
        }, GetHistoryListResp.class);

    }

}
