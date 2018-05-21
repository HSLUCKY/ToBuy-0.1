package com.tobuy.pojo.vo.base;

import java.io.Serializable;
public class BaseResp implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3649062434083263217L;

	protected int retcode;

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
