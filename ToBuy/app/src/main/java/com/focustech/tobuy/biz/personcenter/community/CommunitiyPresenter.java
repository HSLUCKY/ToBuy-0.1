package com.focustech.tobuy.biz.personcenter.community;

import com.focustech.tobuy.bean.service.card.GetCardListResp;
import com.focustech.tobuy.bean.service.card.GetDetailCardResp;
import com.focustech.tobuy.bean.service.card.GetSimpleCardListResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.community.view.ICommunityView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;

/**
 *
 *  uuid    1 普通加载card
 *  uuid    2 排序查找
 *  uuid    3 加载具体的帖子信息
 */

public class CommunitiyPresenter extends BasePresenter<ICommunityView> {

    public void preLoadCards(int type){
        eachLoadCard(1, 0, 20, type);
    }

    /**
     * 每次用户刷新加载帖子
     *
     * @param count 数量
     * @param type  具体类型    类型0表示全部
     */
    public void eachLoadCard(int uuid,int start, int count, final int type){
        mvpView.showLoading();
        OkHttpManager okHttpManager = BridgeFactory.getBridge(Bridges.HTTP);
        okHttpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=community&method=eachLoadCard", getName(), new ITRequestResult<GetSimpleCardListResp>() {
            @Override
            public void onSuccessful(GetSimpleCardListResp entity) {
                mvpView.onSuccess();
                switch (type){
                    case 0:
                        mvpView.loadCards1(entity);
                        break;
                    case 1:
                        mvpView.loadCards2(entity);
                        break;
                    case 2:
                        mvpView.loadCards3(entity);
                        break;
                    case 3:
                        mvpView.loadCards4(entity);
                        break;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                mvpView.onError(errorMsg, "");
            }

            @Override
            public void onCompleted() {

            }
        }, GetSimpleCardListResp.class, new Param("uuid", uuid), new Param("start", start), new Param("count", count), new Param("type", type));
    }


}
