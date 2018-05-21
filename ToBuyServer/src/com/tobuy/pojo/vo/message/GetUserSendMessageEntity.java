package com.tobuy.pojo.vo.message;

import java.util.ArrayList;

import com.tobuy.pojo.MessageTable;

public class GetUserSendMessageEntity {
	public ArrayList<Integer> to_user_ids;
	public ArrayList<String> to_user_images;
	public ArrayList<MessageTable> to_user_messages;

	
	public ArrayList<Integer> getTo_user_ids() {
		return to_user_ids;
	}
	public void setTo_user_ids(ArrayList<Integer> to_user_ids) {
		this.to_user_ids = to_user_ids;
	}
	public ArrayList<String> getTo_user_images() {
		return to_user_images;
	}
	public void String(ArrayList<String> to_user_images) {
		this.to_user_images = to_user_images;
	}
	public ArrayList<MessageTable> getTo_user_messages() {
		return to_user_messages;
	}
	public void setTo_user_messages(ArrayList<MessageTable> to_user_messages) {
		this.to_user_messages = to_user_messages;
	}
	
}
