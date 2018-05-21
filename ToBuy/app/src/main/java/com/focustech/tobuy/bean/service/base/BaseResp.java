package com.focustech.tobuy.bean.service.base;

import java.io.Serializable;

/**
 * <网络请求返回体>
 */
public class BaseResp implements Serializable {
    /**
     * 返回状态码
     */
    protected int retcode;

    /**
     * 返回信息描述
     */
    protected String retinfo;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getRetinfo() {
        return retinfo;
    }

    public void setRetinfo(String retinfo) {
        this.retinfo = retinfo;
    }
}
