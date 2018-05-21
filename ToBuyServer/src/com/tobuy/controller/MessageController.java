package com.tobuy.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tobuy.pojo.vo.message.GetMessagesesResp;
import com.tobuy.pojo.vo.message.SendMessageResp;
import com.tobuy.pojo.vo.user.UserResp;
import com.tobuy.service.MessageService;
import com.tobuy.service.UserService;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
	@Resource
	MessageService messageService;
	@Resource
	UserService userService;

	/**
	 * 获得用户接收到的信息
	 * @param getMessagesesRespJson
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadUserMessages", method = RequestMethod.POST)
	@ResponseBody
	public Object loadGoodsMessage(@RequestBody String getMessagesesRespJson, HttpServletResponse response)
			throws IOException {

		GetMessagesesResp getMessagesesResp = new Gson().fromJson(getMessagesesRespJson, GetMessagesesResp.class);
		messageService.loadUserMessages(getMessagesesResp);
		String resp = new Gson().toJson(getMessagesesResp);
		response.getWriter().write(resp);
		response.flushBuffer();

		return null;
	}

	/**
	 * 获得用户发送的信息
	 * 
	 * @param getMessagesesRespJson
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadUserSendMessages", method = RequestMethod.POST)
	@ResponseBody
	public Object loadUserSendMessages(@RequestBody String getMessagesesRespJson, HttpServletResponse response)
			throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		GetMessagesesResp getMessagesesResp = new Gson().fromJson(getMessagesesRespJson, GetMessagesesResp.class);
		messageService.loadUserSendMessages(getMessagesesResp);
		String resp = gson.toJson(getMessagesesResp);
		response.getWriter().write(resp);
		response.flushBuffer();

		return null;
	}

	/**
	 * 获得和发送对象之间的留言
	 * @param userRespJson
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadUsersInfoByFrom", method = RequestMethod.POST)
	@ResponseBody
	public Object loadUsersInfoByFrom(@RequestBody String getMessagesesRespJson, HttpServletResponse response) throws IOException {
		
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		GetMessagesesResp getMessagesesResp = new Gson().fromJson(getMessagesesRespJson, GetMessagesesResp.class);
		messageService.loadUserMessageByFrom(getMessagesesResp);
		String resp = gson.toJson(getMessagesesResp);
		response.getWriter().write(resp);
		response.flushBuffer();

		return null;
	}
	
	
	/**
	 * 获得简单用户列表的用户信息
	 * 
	 * @param userRespJson
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadUsersInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object loadUsersInfo(@RequestBody String userRespJson, HttpServletResponse response) throws IOException {

		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		UserResp userResp = gson.fromJson(userRespJson, UserResp.class);
		userResp = userService.loadUsersInfo(userResp);
		response.getWriter().write(new Gson().toJson(userResp));
		response.getWriter().flush();

		return null;
	}
	
	/**
	 * 发送信息
	 * @param sendMessageRespJson
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/postMessage", method = RequestMethod.POST)
	@ResponseBody
	public Object postMessage(@RequestBody String sendMessageRespJson, HttpServletResponse response)
			throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		SendMessageResp sendMessageResp = gson.fromJson(sendMessageRespJson, SendMessageResp.class);
		sendMessageResp = messageService.sendMessageByType(sendMessageResp);
		response.getWriter().write(new Gson().toJson(sendMessageResp));
		response.getWriter().flush();

		return null;
	}

	
	
	
	
	
	
	
	
	
	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
