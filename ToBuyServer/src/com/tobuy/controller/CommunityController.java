package com.tobuy.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tobuy.pojo.vo.message.GetMessagesesResp;
import com.tobuy.service.CardService;
import com.tobuy.service.MessageService;

@Controller
@RequestMapping(value="/community")
public class CommunityController {
	
	@Resource
	CardService cardService;
	@Resource
	MessageService messageService;
	
	
	
	@RequestMapping(value="/eachLoadCard", method=RequestMethod.POST)
	@ResponseBody
	public Object eachLoadCard(@RequestBody String rq, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int start = Integer.parseInt(request.getParameter("start"));
		int count = Integer.parseInt(request.getParameter("count"));
		int type = Integer.parseInt(request.getParameter("type"));
		
		response.getWriter().write(new Gson().toJson(cardService.getSimpleCardList(start, count, type)));
		response.flushBuffer();
		
		return null;
	}
	
	
	@RequestMapping(value="/loadMessages", method=RequestMethod.POST)
	@ResponseBody
	public Object loadMessages(@RequestBody String getMessagesesRespJson, HttpServletResponse response) throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		GetMessagesesResp getMessagesesResp = new Gson().fromJson(getMessagesesRespJson, GetMessagesesResp.class);
		response.getWriter().write(gson.toJson(messageService.loadCardMessages(getMessagesesResp)));
		response.flushBuffer();
		
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	

	public CardService getCardService() {
		return cardService;
	}

	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	
}
