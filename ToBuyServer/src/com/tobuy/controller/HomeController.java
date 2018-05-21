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
import com.tobuy.constants.ResponseConstants;
import com.tobuy.pojo.vo.base.BaseResp;
import com.tobuy.pojo.vo.message.GetMessagesesResp;
import com.tobuy.service.GoodsService;
import com.tobuy.service.MessageService;
import com.tobuy.service.ResourceService;



@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Resource
	public GoodsService goodsService;
	@Resource
	public MessageService messageService;
	@Resource
	ResourceService resourceService;
	
	/**
	 * 通过数量获得商品
	 * @param rp
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/eachLoad", method=RequestMethod.POST)
	@ResponseBody
    public Object eachLoad(@RequestBody String rp, HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		RequestParam rpInstance = new Gson().fromJson(rp, RequestParam.class);
		response.getWriter().write(new Gson().toJson(goodsService.getGoodsByCount(rpInstance.getStart(), rpInstance.getCount())));
		response.getWriter().flush();
	
		return null;
    }
	
	/**
	 * 获取商品信息
	 * @param getMessagesesRespJson
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value ="/loadGoodsMessages", method=RequestMethod.POST)
	@ResponseBody
	public Object loadGoodsMessage(@RequestBody String getMessagesesRespJson, HttpServletResponse response) throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		GetMessagesesResp getMessagesesResp = new Gson().fromJson(getMessagesesRespJson, GetMessagesesResp.class);
		messageService.loadMessageses(getMessagesesResp);
		String resp = gson.toJson(getMessagesesResp);
		response.getWriter().write(resp);
		response.flushBuffer();
		
		return null;
	}
	
	
	/**
	 * 第一次登录获取用户的头像
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/loadUserHead", method=RequestMethod.POST)
	@ResponseBody
	public Object loadUserHead(@RequestBody String userHeadRespJson, HttpServletResponse response) throws IOException {
		UserHeadResp userHeadResp = new Gson().fromJson(userHeadRespJson, UserHeadResp.class);
		userHeadResp.setHead(resourceService.loadUserHeadById(userHeadResp.getId()));
		userHeadResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		response.getWriter().write(new Gson().toJson(userHeadResp));
		response.getWriter().flush();
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public GoodsService getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	
}


class RequestParam {
    public int uuid;
    public int start;
    public int count;
    public String view;
    public String method;
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}

class UserHeadResp extends BaseResp{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  int id;
    public byte[] head;

    public byte[] getHead() {
        return head;
    }

    public void setHead(byte[] head) {
        this.head = head;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
