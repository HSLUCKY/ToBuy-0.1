package com.tobuy.util;

import java.security.MessageDigest;



public class MD5Util {
	//定义16进制字符列表
	public static String[] hexs = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	//加密方法
	public static String encode(String password){
		try {
			//创建MD5加密对象
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			//我要得到字节数组
			byte[] bytes= md5.digest(password.getBytes());
			//把字节数组转化成十六进制格式的字符
			return bytesToHexStrings(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//把单个字节转化成对应的十六进制格式的字符丿
	private static String byteToHexString(byte b){
		int i = b;
		if(i<0){
			i = i+256;
		}
		int ge = i%16;
		int shi = i/16;
		StringBuffer sb = new StringBuffer();
		return sb.append(hexs[shi]).append(hexs[ge]).toString(); 
	}
	//把字节数组转换成16进制格式的字符串
	private static String bytesToHexStrings(byte[] bytes){
		StringBuffer sb = new StringBuffer();
		for(byte b:bytes){
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}
	
	
}
