package com.focustech.tobuy.bean.service.personal;

import com.focustech.tobuy.bean.service.base.BaseResp;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/2.
 */

public class SendSomeUserResp extends BaseResp {

    /**
     * 用户字段值键值对
     */
    public HashMap<String, Object> fields;

    public HashMap<String, Object> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, Object> fields) {
        this.fields = fields;
    }
}
