package com.tobuy.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GetBytesUtil {

	public static byte[] getByteBySrc(String src) {
		
		byte[] des = null;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		BufferedInputStream bis = null;
		
		try {
			 bis = new BufferedInputStream(new FileInputStream(new File(src)));
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = bis.read(buffer))!=-1) {
				dos.write(buffer, 0, len);
			}
			
			des = baos.toByteArray();
			
			dos.flush();
			bis.close();
			dos.close();
			baos.close();
		} catch (IOException e) {e.printStackTrace();}
		
		return des;
	}
	
}
