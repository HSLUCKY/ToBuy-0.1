package com.focustech.tobuy.biz.personcenter.community.view;

import com.focustech.tobuy.bean.service.card.GetSimpleCardListResp;
import com.focustech.tobuy.biz.base.IMvpView;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface ICommunityView extends IMvpView {
    public abstract void loadCards1(GetSimpleCardListResp entity);
    public abstract void loadCards2(GetSimpleCardListResp entity);
    public abstract void loadCards3(GetSimpleCardListResp entity);
    public abstract void loadCards4(GetSimpleCardListResp entity);
}
