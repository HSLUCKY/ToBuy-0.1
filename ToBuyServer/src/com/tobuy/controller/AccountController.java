package com.tobuy.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tobuy.constants.ResponseConstants;
import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.login.LoginResp;
import com.tobuy.pojo.vo.user.UserResp;
import com.tobuy.service.AccountService;
import com.tobuy.util.JsonMapUtil;

@Controller
@RequestMapping(value=("/account"))
public class AccountController {

	@Resource
	private AccountService accountService;

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Object register(@RequestBody String token, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (token == null) {
			return null;
		}
		HashMap<String, String> registerTokes = JsonMapUtil.getMap(token);

		if (registerTokes == null) {
			return null;
		}

		System.out.println(registerTokes.get("username") + " " + registerTokes.get("password"));
		UserTable userTable = new UserTable();
		userTable.setUsername(registerTokes.get("username"));
		userTable.setPassword(registerTokes.get("password"));
		userTable.setHead(1);

		HashMap<String, Object> registerResp = new HashMap<String, Object>();
		if (accountService.add(userTable) == 1) {
			registerResp.put("retcode", Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		} else {
			registerResp.put("retcode", Integer.parseInt(ResponseConstants.FAILED_CODE_END));
		}
		JSONObject jsonObject = new JSONObject(registerResp);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(jsonObject.toString());;
		response.flushBuffer();
		
		return null;
	}

	@RequestMapping(value =("/login"), method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody String token, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (token == null) {
			return null;
		}

		HashMap<String, String> tokenMap = JsonMapUtil.getMap(token);

		if (tokenMap == null) {
			return null;
		}

		System.out.println(tokenMap.get("username") + " " + tokenMap.get("password"));

		LoginResp loginResp = accountService.getUserTableByLogin(tokenMap);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(new Gson().toJson(loginResp));
		response.flushBuffer();
		
		return null;
	}

	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
	@ResponseBody
	public Object updateUserInfo(@RequestBody String userTableJson, HttpServletResponse response) throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss").create();
		UserTable userTable = gson.fromJson(userTableJson,  UserTable.class);
		UserResp userResp = accountService.updateAccount(userTable);
		//May 2, 2018 00:00:00
		response.getWriter().write(gson.toJson(userResp));
		response.flushBuffer();
		
		return null;
		
	}
	
	
	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
