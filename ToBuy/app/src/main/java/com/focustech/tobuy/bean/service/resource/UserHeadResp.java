package com.focustech.tobuy.bean.service.resource;

import com.focustech.tobuy.bean.service.base.BaseResp;

/**
 * Created by Administrator on 2018/5/12.
 */

public class UserHeadResp extends BaseResp {
    public  int id;
    public byte[] head;

    public byte[] getHead() {
        return head;
    }

    public void setHead(byte[] head) {
        this.head = head;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
