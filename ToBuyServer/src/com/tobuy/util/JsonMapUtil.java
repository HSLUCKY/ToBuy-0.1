package com.tobuy.util;

import java.util.HashMap;

public class JsonMapUtil {
	
	public static HashMap<String, String> getMap(String token) {
		String[] baseToken = token.split("&");
		HashMap<String, String> registerTokens = new HashMap<String, String>();
		for (String string : baseToken) {
			String[] entryToken = string.split("=");
			if(entryToken.length < 2){
				return null;
			}else {
				registerTokens.put(entryToken[0], entryToken[1]);
			}
		}
		return registerTokens;
	}
	
}
