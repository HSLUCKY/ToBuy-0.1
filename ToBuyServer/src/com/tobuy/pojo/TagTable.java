package com.tobuy.pojo;

import java.io.Serializable;

public class TagTable implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -379419632148218494L;

	private Integer id;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}