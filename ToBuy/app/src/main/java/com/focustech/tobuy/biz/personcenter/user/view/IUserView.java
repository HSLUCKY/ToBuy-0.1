package com.focustech.tobuy.biz.personcenter.user.view;

import com.focustech.tobuy.bean.service.user.UserResp;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.base.IMvpView;

/**
 * Created by Administrator on 2018/5/12.
 */

public interface IUserView extends IMvpView {
    public abstract void loadUserInfo(UserResp userResp);
    public abstract void loadUserById(UserTable userTable);
    public abstract void updateUserInfo(UserTable userTable);
}
