package com.tobuy.util;

import java.security.MessageDigest;



public class MD5Util {
	//����16�����ַ��б�
	public static String[] hexs = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	//���ܷ���
	public static String encode(String password){
		try {
			//����MD5���ܶ���
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			//��Ҫ�õ��ֽ�����
			byte[] bytes= md5.digest(password.getBytes());
			//���ֽ�����ת����ʮ�����Ƹ�ʽ���ַ�
			return bytesToHexStrings(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//�ѵ����ֽ�ת���ɶ�Ӧ��ʮ�����Ƹ�ʽ���ַ�د
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
	//���ֽ�����ת����16���Ƹ�ʽ���ַ���
	private static String bytesToHexStrings(byte[] bytes){
		StringBuffer sb = new StringBuffer();
		for(byte b:bytes){
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}
	
	
}
