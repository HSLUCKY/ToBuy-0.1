package com.tobuy.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tobuy.mapper.ResourceTableMapper;
import com.tobuy.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Resource
	ResourceTableMapper resourceTableMapper;
	
	/**
	 * 直接通过链接返回用户头像
	 * 后期可以结合pisco解决图片加载速度慢的问题s
	 */
	@Override
	public byte[] loadUserHeadById(int id) {
		
		String src = resourceTableMapper.selectSrcByUserId(id);
		
		byte[] head = new byte[512];
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(src)));
			
			int len = -1;
			byte[] buffer = new byte[512];
			while ((len = bis.read(buffer)) != -1) {
				dos.write(buffer, 0, len);
			}
			dos.flush();
			head = baos.toByteArray();
			baos.close();
			dos.close();
			bis.close();
			
		} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		
		
		return head;
	}

}
