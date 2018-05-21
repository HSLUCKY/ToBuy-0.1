package com.focustech.tobuy.biz.personcenter.tag;

import android.nfc.Tag;

import com.focustech.tobuy.bean.service.tag.TagsResp;
import com.focustech.tobuy.biz.base.BasePresenter;
import com.focustech.tobuy.biz.personcenter.tag.view.ITagView;
import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.capabilities.http.ITRequestResult;
import com.focustech.tobuy.capabilities.http.Param;
import com.focustech.tobuy.constant.URLUtil;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/5/11.
 */

public class TagsPresenter extends BasePresenter<ITagView> {

    /**
     * 获取服务器所有的标签项
     */
    public void getAllTags(){
        OkHttpManager okHttpManager = BridgeFactory.getBridge(Bridges.HTTP);

        okHttpManager.requestAsyncPostByTag(URLUtil.FORWARD_PORT + "?view=publish&method=getAllTags", getName() , new ITRequestResult<TagsResp>(){

            @Override
            public void onSuccessful(TagsResp entity) {
                mvpView.loadAllTags(entity);
            }

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onCompleted() {

            }
        },TagsResp.class, new Param("tagsResp", new Gson().toJson(new TagsResp())));

    }

}
