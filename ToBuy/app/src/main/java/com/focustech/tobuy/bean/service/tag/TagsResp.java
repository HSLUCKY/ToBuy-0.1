package com.focustech.tobuy.bean.service.tag;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.TagTable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/2.
 */

public class TagsResp extends BaseResp {

    /**
     * 持有者id
     */
    public int id;

    /**
     * 持有者类型
     */
    public int type;

    /**
     * 标签组
     */
    public ArrayList<TagTable> tags;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<TagTable> getTags() {
        return tags;
    }

    public void setTags(ArrayList<TagTable> tags) {
        this.tags = tags;
    }
}
