package com.focustech.tobuy.biz.personcenter.search;

import com.focustech.tobuy.bean.service.goods.GetGoodsResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.search.view.ISearchView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;

/**
 * uuid 从 6 开始因为使用同一个连接
 */

public class SearchPresenter extends BasePresenter<ISearchView> {

    /**
     * 模糊查询
     * @param key   关键字
     */
    public void search(String key){
        //加载信息
        mvpView.showLoading();
        OkHttpManager httpManager = BridgeFactory.getBridge(Bridges.HTTP);

        httpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=search&method=searchGoodsByKey", getName(), new ITRequestResult<GetGoodsResp>() {

            @Override
            public void onSuccessful(GetGoodsResp entity) {
                mvpView.loadGoodsByKey(entity);
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, null);
            }

            @Override
            public void onCompleted() {

            }
        }, GetGoodsResp.class, new Param("key", key));
    }

}
