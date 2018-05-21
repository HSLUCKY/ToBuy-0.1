package com.focustech.tobuy.biz.personcenter.resource.view;

import android.widget.ImageView;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.service.resource.ResourceResp;
import com.focustech.tobuy.bean.service.resource.UserHeadResp;
import com.focustech.tobuy.biz.base.IMvpView;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface IResourceView extends IMvpView {

    /**
     * 界面和Presenter的回调通道
     * @param resourceResp
     */
    public abstract void loadResources(ResourceResp resourceResp);

    public abstract void loadHead(UserHeadResp userHeadResp);
}
