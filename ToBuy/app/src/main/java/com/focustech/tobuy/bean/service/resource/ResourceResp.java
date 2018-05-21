package com.focustech.tobuy.bean.service.resource;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.ResourceTable;
import com.focustech.tobuy.bean.table.entity.TagTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 上传资源信息
 */

public class ResourceResp extends BaseResp {
    /**
     * 持有者id
     */
    int id;

    /**
     * 持有者类型
     * 确定哪张关联表
     * 1 Card
     * 2 Goods
     * 3 Message
     */
    int type;

    /**
     * 资源信息
     * 存储资源
     * 用户上传的难免有重复资源
     * 服务器提供的不允许重复
     * （可以像百度云那样判断相似度
     *  但是对服务器要求较高）
     */

    private HashMap<String, Object> resources;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, Object> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, Object> resources) {
        this.resources = resources;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
