package com.tobuy.pojo.vo.tag;

import java.util.ArrayList;

import com.tobuy.pojo.TagTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class TagsResp extends BaseResp {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3462758621865725649L;

	public int id;

    public int type;

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
