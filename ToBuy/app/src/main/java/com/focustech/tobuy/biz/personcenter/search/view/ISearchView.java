package com.focustech.tobuy.biz.personcenter.search.view;

import com.focustech.tobuy.bean.service.goods.GetGoodsResp;
import com.focustech.tobuy.biz.base.IMvpView;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface ISearchView extends IMvpView {

    public abstract void loadGoodsByKey(GetGoodsResp getGoodsResp);

}
