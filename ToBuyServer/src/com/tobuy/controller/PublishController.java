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
import com.tobuy.pojo.vo.card.PublishCardResp;
import com.tobuy.pojo.vo.tag.TagsResp;
import com.tobuy.service.CardService;
import com.tobuy.service.TagService;

@Controller
@RequestMapping(value="/publish")
public class PublishController {
	
	@Resource 
	TagService tagService;
	@Resource
	CardService cardService;

	@RequestMapping(value="/getAllTags", method=RequestMethod.POST)
	@ResponseBody
	public Object getAllTags(@RequestBody String tagsRespJson, HttpServletResponse response) throws IOException {

		TagsResp tagsResp = new Gson().fromJson(tagsRespJson, TagsResp.class);
		tagsResp = tagService.getAllTagsResp(tagsResp);
		response.getWriter().write(new Gson().toJson(tagsResp));
		response.getWriter().flush();
		
		return null;
	}
	
	
	
	
	@RequestMapping(value="/publishCard", method=RequestMethod.POST)
	@ResponseBody
	public Object publishCard(@RequestBody String publishCardRespJson, HttpServletResponse response) throws IOException {
		//May 11, 2018 15:01:27
		System.out.println(publishCardRespJson);
		
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		
		PublishCardResp publishCardResp = gson.fromJson(publishCardRespJson, PublishCardResp.class);
		publishCardResp = cardService.publishCard(publishCardResp);
		response.getWriter().write(new Gson().toJson(publishCardResp));
		response.getWriter().flush();
		
		return null;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public TagService getTagService() {
		return tagService;
	}




	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}




	public CardService getCardService() {
		return cardService;
	}




	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}
	
	
	
}
