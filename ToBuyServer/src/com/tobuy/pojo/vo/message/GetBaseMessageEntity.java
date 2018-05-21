package com.tobuy.pojo.vo.message;

import java.util.ArrayList;

import com.tobuy.pojo.MessageTable;

/**
 * 用于返回基本留言
 * @author Administrator
 *
 */
public class GetBaseMessageEntity{
	
	public ArrayList<Integer> uid;
	public ArrayList<String> images;
	public ArrayList<MessageTable> messageTables;
	
	
	public ArrayList<Integer> getUid() {
		return uid;
	}
	public void setUid(ArrayList<Integer> uid) {
		this.uid = uid;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	public ArrayList<MessageTable> getMessageTables() {
		return messageTables;
	}
	public void setMessageTables(ArrayList<MessageTable> messageTables) {
		this.messageTables = messageTables;
	}
	
	
}
