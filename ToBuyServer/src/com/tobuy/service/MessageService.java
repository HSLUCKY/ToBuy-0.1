package com.tobuy.service;

import com.tobuy.pojo.vo.message.GetMessagesesResp;
import com.tobuy.pojo.vo.message.SendMessageResp;

public interface MessageService {
	public abstract GetMessagesesResp loadMessageses(GetMessagesesResp getMessagesesResp);
	
	public abstract GetMessagesesResp loadCardMessages(GetMessagesesResp getMessagesesResp);
	
	public abstract GetMessagesesResp loadUserMessages(GetMessagesesResp getMessagesesResp);
	
	public abstract GetMessagesesResp loadUserSendMessages(GetMessagesesResp getMessagesesResp);
	
	public abstract SendMessageResp sendMessageByType(SendMessageResp sendMessageResp);
	
	public abstract GetMessagesesResp loadUserMessageByFrom(GetMessagesesResp getMessagesesResp);
}
