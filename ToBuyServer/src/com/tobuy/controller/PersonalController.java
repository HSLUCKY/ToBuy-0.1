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
import com.tobuy.pojo.vo.goods.SendGoodsResp;
import com.tobuy.pojo.vo.user.UserResp;
import com.tobuy.service.GoodsService;
import com.tobuy.service.UserService;

@Controller
@RequestMapping(value = "/personal")
public class PersonalController {
	
	@Resource
	GoodsService goodsService;
	@Resource
	UserService userService;

	@RequestMapping(value = "/publishGoods", method = RequestMethod.POST)
	@ResponseBody
	public Object publishGoods(@RequestBody String sendGoodsRespJson, HttpServletResponse response) throws IOException {

		// May 11, 2018 15:01:27
		System.out.println(sendGoodsRespJson);

		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		SendGoodsResp sendGoodsResp = gson.fromJson(sendGoodsRespJson, SendGoodsResp.class);
		sendGoodsResp = goodsService.publishGoods(sendGoodsResp);
		response.getWriter().write(new Gson().toJson(sendGoodsResp));
		response.flushBuffer();
		
		return null;
	}
	
	
	
	@RequestMapping(value = "/loadUser", method = RequestMethod.POST)
	@ResponseBody
	public Object loadUser(@RequestBody String idJson, HttpServletResponse response) throws IOException {

		 Integer id = new Gson().fromJson(idJson, Integer.class);
		 UserResp userResp = new UserResp();
		 userResp.setUid(id);
		 userResp = userService.loadUserById(userResp);
		
		response.getWriter().write(new Gson().toJson(userResp));
		response.flushBuffer();
		
		return null;
	}
	
	
	

}
