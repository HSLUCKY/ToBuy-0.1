package com.tobuy.pojo;

import java.io.Serializable;
import java.util.Date;

public class MessageTable implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4626966850191342565L;

	private Integer id;

    private Date date;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}