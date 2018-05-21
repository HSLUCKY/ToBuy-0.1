package com.focustech.tobuy.biz.personcenter.tag.view;

import com.focustech.tobuy.bean.service.tag.TagsResp;
import com.focustech.tobuy.biz.base.IMvpView;

/**
 * Created by Administrator on 2018/5/11.
 */

public interface ITagView extends IMvpView {
    public abstract void loadAllTags(TagsResp tagsResp);
}
