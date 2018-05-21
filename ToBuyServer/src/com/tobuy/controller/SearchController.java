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
import com.tobuy.pojo.vo.goods.GetGoodsResp;
import com.tobuy.service.GoodsService;

@Controller
@RequestMapping(value="/search")
public class SearchController {
	
	@Resource
	GoodsService goodsService;
	
	@RequestMapping(value="/searchGoodsByKey", method=RequestMethod.POST)
	@ResponseBody
	public Object searchGoodsByKey(@RequestBody String searchKeyJson, HttpServletResponse response) throws IOException {
		String key = new Gson().fromJson(searchKeyJson, String.class);
		GetGoodsResp getGoodsResp = goodsService.searchGoodsByKey(key);
		response.getWriter().write(new Gson().toJson(getGoodsResp));
		response.getWriter().flush();
		return null;
	}
	

}
