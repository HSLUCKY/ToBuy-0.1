package com.tobuy.pojo;

import java.io.Serializable;

public class ResourceTable implements Serializable {
	private static final long serialVersionUID = 1225016934107781582L;

	private Integer id;

    private Integer type;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}